package com.silkycoders1.jsystemssilkycodders1.agui.protocol.event;

public record TextMessageStartEvent(String type, Long timestamp, String message_id, String role) implements BaseEvent {

    public TextMessageStartEvent(String messageId, String role) {
        this("TEXT_MESSAGE_START", System.currentTimeMillis(), messageId, role);
    }
}
