package com.royalrangers.utils;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.royalrangers.bean.ResponseResult;
import lombok.Getter;

public class ResponseBuilder {

    public static ResponseResult success() {
        return new ResponseResult(true, new EmptyJsonResponse());
    }

    public static ResponseResult success(Object data) {
        return new ResponseResult(true, data);
    }

    public static ResponseResult fail() {
        return new ResponseResult(false, new EmptyJsonResponse());
    }

    public static ResponseResult fail(String message) {
        return new ResponseResult(false, new ResponseMessage(message));
    }

    @JsonSerialize
    private static class EmptyJsonResponse { }

    @Getter
    @JsonSerialize
    private static class ResponseMessage{
        private String message;

        ResponseMessage (String message) {
            this.message = message;
        }
    }
}
