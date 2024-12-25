package com.rafaelhosaka.rhv.user.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum ErrorCode {
    US_SUCCESS("US000"),
    US_ENTITY_NOT_FOUND("US001"),
    US_EXCEPTION("US100"),
    //EXTERNAL
    VS_ENTITY_NOT_FOUND("VS001");

    private final String code;

    ErrorCode(String code) {
        this.code = code;
    }

    @JsonValue
    public String getCode() {
        return code;
    }

    @JsonCreator
    public static ErrorCode fromCode(String code) {
        for (ErrorCode errorCode : values()) {
            if (errorCode.code.equals(code)) {
                return errorCode;
            }
        }
        return US_SUCCESS;
    }
}
