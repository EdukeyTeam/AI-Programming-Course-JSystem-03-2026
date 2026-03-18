package com.silkycoders1.jsystemssilkycodders1.agui.protocol.event;

public record RunFinishedEvent(String type, Long timestamp, String thread_id, String run_id) implements BaseEvent {

    public RunFinishedEvent(String threadId, String runId) {
        this("RUN_FINISHED", System.currentTimeMillis(), threadId, runId);
    }
}
