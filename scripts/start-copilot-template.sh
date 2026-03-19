#!/usr/bin/env bash
set -euo pipefail

log() {
  printf '[start-copilot-template] %s\n' "$1"
}

ensure_java() {
  if command -v java >/dev/null 2>&1; then
    return
  fi

  local candidates=()
  [ -n "${JAVA_HOME:-}" ] && candidates+=("$JAVA_HOME")
  [ -n "${HOME:-}" ] && candidates+=("$HOME/.jdks")
  [ -n "${USERPROFILE:-}" ] && candidates+=("$USERPROFILE/.jdks")
  candidates+=("/d/lucas/.jdks")

  local candidate root
  for root in "${candidates[@]}"; do
    [ -e "$root" ] || continue

    if [ -x "$root/bin/java" ]; then
      export JAVA_HOME="$root"
      export PATH="$JAVA_HOME/bin:$PATH"
      log "Using JDK from $JAVA_HOME."
      return
    fi

    candidate="$(find "$root" -mindepth 1 -maxdepth 1 -type d 2>/dev/null | sort -r | head -n 1 || true)"
    if [ -n "$candidate" ] && [ -x "$candidate/bin/java" ]; then
      export JAVA_HOME="$candidate"
      export PATH="$JAVA_HOME/bin:$PATH"
      log "Using JDK from $JAVA_HOME."
      return
    fi
  done

  echo "Java 21 is required. Set JAVA_HOME or install a JDK." >&2
  exit 1
}

SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
REPO_ROOT="$(cd "$SCRIPT_DIR/.." && pwd)"

BACKEND_LOG="$REPO_ROOT/backend/backend.log"
RUN_ID="$(date +%Y%m%d-%H%M%S)"
FRONTEND_PORT="${PORT:-3000}"
BACKEND_LOG="$REPO_ROOT/backend/backend-$RUN_ID.log"
FRONTEND_LOG="$REPO_ROOT/frontend/frontend-$RUN_ID.log"
FRONTEND_ERR_LOG="$REPO_ROOT/frontend/frontend-$RUN_ID.err.log"

cd "$REPO_ROOT"

ensure_java

if [ -f ./mvnw ] && [ -f ./pom.xml ]; then
  log "Starting backend using root Maven reactor."
  nohup ./mvnw package spring-boot:test-run -pl langgraph4j-ag-ui-sdk >"$BACKEND_LOG" 2>&1 &
elif [ -f ./backend/mvnw ]; then
  log "Root Maven reactor not found. Falling back to backend module start."
  (
    cd backend
    nohup ./mvnw spring-boot:run >"$BACKEND_LOG" 2>&1 &
  )
else
  log "Backend start command not available."
fi

if [ -f ./frontend/package.json ] && command -v npm >/dev/null 2>&1; then
  log "Starting frontend."
  (
    cd frontend
    nohup npm run dev >"$FRONTEND_LOG" 2>"$FRONTEND_ERR_LOG" &
  )
else
  log "Frontend package or npm not available."
fi

log "Started processes. Backend log: $BACKEND_LOG"
log "Started processes. Frontend log: $FRONTEND_LOG"
log "App URL: http://localhost:$FRONTEND_PORT"
