$ErrorActionPreference = "Stop"

function Write-Info {
    param([string]$Message)
    Write-Host "[start-copilot-template] $Message"
}

function Initialize-Java {
    if ($env:JAVA_HOME -and (Test-Path (Join-Path $env:JAVA_HOME "bin\java.exe"))) {
        $env:Path = "$(Join-Path $env:JAVA_HOME 'bin');$env:Path"
        return
    }

    if (Get-Command java -ErrorAction SilentlyContinue) {
        return
    }

    $candidates = @()
    if ($env:USERPROFILE) {
        $candidates += Join-Path $env:USERPROFILE ".jdks"
    }
    $candidates += "D:\lucas\.jdks"

    foreach ($candidateRoot in $candidates | Select-Object -Unique) {
        if (-not (Test-Path $candidateRoot)) {
            continue
        }

        $jdk = Get-ChildItem -Path $candidateRoot -Directory |
            Sort-Object Name -Descending |
            Select-Object -First 1

        if ($jdk -and (Test-Path (Join-Path $jdk.FullName "bin\java.exe"))) {
            $env:JAVA_HOME = $jdk.FullName
            $env:Path = "$(Join-Path $env:JAVA_HOME 'bin');$env:Path"
            Write-Info "Using JDK from $($env:JAVA_HOME)."
            return
        }
    }

    throw "Java 21 is required. Set JAVA_HOME or install a JDK."
}

$repoRoot = Split-Path -Parent $PSScriptRoot
$runId = Get-Date -Format "yyyyMMdd-HHmmss"
$frontendPort = if ($env:PORT) { $env:PORT } else { "3000" }
$backendLog = Join-Path $repoRoot "backend\backend-$runId.log"
$backendErrLog = Join-Path $repoRoot "backend\backend-$runId.err.log"
$frontendLog = Join-Path $repoRoot "frontend\frontend-$runId.log"
$frontendErrLog = Join-Path $repoRoot "frontend\frontend-$runId.err.log"

Push-Location $repoRoot

try {
    Initialize-Java

    if ((Test-Path ".\mvnw.cmd") -and (Test-Path ".\pom.xml")) {
        Write-Info "Starting backend using root Maven reactor."
        Start-Process -FilePath "cmd.exe" `
            -ArgumentList "/c", "mvnw.cmd package spring-boot:test-run -pl langgraph4j-ag-ui-sdk" `
            -WorkingDirectory $repoRoot `
            -RedirectStandardOutput $backendLog `
            -RedirectStandardError $backendErrLog `
            -WindowStyle Hidden | Out-Null
    }
    elseif (Test-Path ".\backend\mvnw.cmd") {
        Write-Info "Root Maven reactor not found. Falling back to backend module start."
        Start-Process -FilePath "cmd.exe" `
            -ArgumentList "/c", ".\mvnw.cmd spring-boot:run" `
            -WorkingDirectory (Join-Path $repoRoot "backend") `
            -RedirectStandardOutput $backendLog `
            -RedirectStandardError $backendErrLog `
            -WindowStyle Hidden | Out-Null
    }
    else {
        Write-Warning "Backend start command not available."
    }

    if ((Test-Path ".\frontend\package.json") -and (Get-Command npm -ErrorAction SilentlyContinue)) {
        Write-Info "Starting frontend."
        Start-Process -FilePath "npm.cmd" `
            -ArgumentList "run", "dev" `
            -WorkingDirectory (Join-Path $repoRoot "frontend") `
            -RedirectStandardOutput $frontendLog `
            -RedirectStandardError $frontendErrLog `
            -WindowStyle Hidden | Out-Null
    }
    else {
        Write-Warning "Frontend package or npm not available."
    }

    Write-Info "Started processes. Backend log: $backendLog"
    Write-Info "Started processes. Backend error log: $backendErrLog"
    Write-Info "Started processes. Frontend log: $frontendLog"
    Write-Info "App URL: http://localhost:$frontendPort"
}
finally {
    Pop-Location
}
