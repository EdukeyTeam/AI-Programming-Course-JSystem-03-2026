package com.silkycoders1.jsystemssilkycodders1.agui.protocol.event;

public record ToolCallArgsEvent(String type, Long timestamp, String tool_call_id, String tool_call_args) implements BaseEvent {

    public ToolCallArgsEvent(String toolArgs, String toolCallId) {
        this("TOOL_CALL_ARGS", System.currentTimeMillis(), toolCallId, toolArgs);
    }
}
