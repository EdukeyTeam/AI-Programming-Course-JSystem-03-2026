package com.silkycoders1.jsystemssilkycodders1.agui.protocol;

import com.silkycoders1.jsystemssilkycodders1.agui.protocol.event.RunFinishedEvent;
import com.silkycoders1.jsystemssilkycodders1.agui.protocol.event.RunStartedEvent;
import com.silkycoders1.jsystemssilkycodders1.agui.protocol.event.TextMessageContentEvent;
import com.silkycoders1.jsystemssilkycodders1.agui.protocol.event.TextMessageEndEvent;
import com.silkycoders1.jsystemssilkycodders1.agui.protocol.event.TextMessageStartEvent;
import com.silkycoders1.jsystemssilkycodders1.agui.protocol.event.ToolCallArgsEvent;
import com.silkycoders1.jsystemssilkycodders1.agui.protocol.event.ToolCallEndEvent;
import com.silkycoders1.jsystemssilkycodders1.agui.protocol.event.ToolCallStartEvent;

public final class EventFactory {

    private EventFactory() {
    }

    public static RunStartedEvent runStartedEvent(String threadId, String runId) {
        return new RunStartedEvent(threadId, runId);
    }

    public static RunFinishedEvent runFinishedEvent(String threadId, String runId) {
        return new RunFinishedEvent(threadId, runId);
    }

    public static TextMessageStartEvent textMessageStartEvent(String messageId, String role) {
        return new TextMessageStartEvent(messageId, role);
    }

    public static TextMessageContentEvent textMessageContentEvent(String messageId, String delta) {
        return new TextMessageContentEvent(messageId, delta);
    }

    public static TextMessageEndEvent textMessageEndEvent(String messageId) {
        return new TextMessageEndEvent(messageId);
    }

    public static ToolCallStartEvent toolCallStartEvent(String messageId, String toolName, String toolCallId) {
        return new ToolCallStartEvent(messageId, toolName, toolCallId);
    }

    public static ToolCallArgsEvent toolCallArgsEvent(String toolArgs, String toolCallId) {
        return new ToolCallArgsEvent(toolArgs, toolCallId);
    }

    public static ToolCallEndEvent toolCallEndEvent(String toolCallId) {
        return new ToolCallEndEvent(toolCallId);
    }
}
