package com.silkycoders1.jsystemssilkycodders1.agui.protocol;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public record State(Map<String, Object> value) {
}
