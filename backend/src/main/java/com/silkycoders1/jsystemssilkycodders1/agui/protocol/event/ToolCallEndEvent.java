package com.silkycoders1.jsystemssilkycodders1.agui.protocol.event;

public record ToolCallEndEvent(String type, Long timestamp, String tool_call_id) implements BaseEvent {

    public ToolCallEndEvent(String toolCallId) {
        this("TOOL_CALL_END", System.currentTimeMillis(), toolCallId);
    }
}
