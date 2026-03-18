package com.silkycoders1.jsystemssilkycodders1.agui.protocol.event;

public record ToolCallStartEvent(
        String type,
        Long timestamp,
        String tool_call_id,
        String tool_call_name,
        String parent_message_id
) implements BaseEvent {

    public ToolCallStartEvent(String messageId, String toolName, String toolCallId) {
        this("TOOL_CALL_START", System.currentTimeMillis(), toolCallId, toolName, messageId);
    }
}
