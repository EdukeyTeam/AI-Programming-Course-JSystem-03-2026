package com.silkycoders1.jsystemssilkycodders1.agui.protocol;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Context(String key, Object value) {
}
