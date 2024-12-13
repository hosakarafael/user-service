package com.rafaelhosaka.rhv.user.dto;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ErrorCode {
    DEFAULT("US000"),
    ENTITY_NOT_FOUND("US001"),
    EXCEPTION("US100");

    private final String code;

    ErrorCode(String code) {
        this.code = code;
    }

    @JsonValue
    public String getCode() {
        return code;
    }
}
