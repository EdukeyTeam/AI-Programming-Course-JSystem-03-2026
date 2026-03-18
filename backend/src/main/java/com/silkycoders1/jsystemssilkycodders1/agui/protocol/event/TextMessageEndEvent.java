package com.silkycoders1.jsystemssilkycodders1.agui.protocol.event;

public record TextMessageEndEvent(String type, Long timestamp, String message_id) implements BaseEvent {

    public TextMessageEndEvent(String messageId) {
        this("TEXT_MESSAGE_END", System.currentTimeMillis(), messageId);
    }
}
