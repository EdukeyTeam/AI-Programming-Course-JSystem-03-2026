package com.silkycoders1.jsystemssilkycodders1.agui.protocol.event;

public record RunStartedEvent(String type, Long timestamp, String thread_id, String run_id) implements BaseEvent {

    public RunStartedEvent(String threadId, String runId) {
        this("RUN_STARTED", System.currentTimeMillis(), threadId, runId);
    }
}
