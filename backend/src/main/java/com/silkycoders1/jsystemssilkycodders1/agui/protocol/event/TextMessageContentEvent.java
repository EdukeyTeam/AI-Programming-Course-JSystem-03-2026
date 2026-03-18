package com.silkycoders1.jsystemssilkycodders1.agui.protocol.event;

public record TextMessageContentEvent(String type, Long timestamp, String message_id, String delta) implements BaseEvent {

    public TextMessageContentEvent(String messageId, String delta) {
        this("TEXT_MESSAGE_CONTENT", System.currentTimeMillis(), messageId, delta);
    }
}
