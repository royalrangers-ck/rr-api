package com.royalrangers.bean;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class ResultResponse implements Serializable {

    private Object data = new EmptyJsonResponse();
    private boolean success = false;

    public ResultResponse(boolean success, Object data) {
        this.data = data;
        this.success = success;
    }

    public ResultResponse(boolean success, String message) {
        this.data = new ResponseMessage(message);
        this.success = success;
    }

    public ResultResponse(boolean success) {
        this.success = success;
    }

    @JsonSerialize
    public class EmptyJsonResponse { }

    @Getter
    public class ResponseMessage implements Serializable{
        private String message;
        ResponseMessage (String message) {
            this.message = message;
        }
    }
}
