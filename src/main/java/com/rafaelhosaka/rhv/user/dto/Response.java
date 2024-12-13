package com.rafaelhosaka.rhv.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response {
    private String message = "";
    private ErrorCode errorCode = ErrorCode.DEFAULT;

    public Response(String message){
        this.message = message;
    }
}
