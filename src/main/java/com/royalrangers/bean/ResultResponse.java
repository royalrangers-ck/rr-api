package com.royalrangers.bean;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.io.Serializable;

public class ResultResponse implements Serializable {

    private Object data = new EmptyJsonResponse();
    private boolean success = false;

    private ResultResponse() {
    }

    public Object getData() {
        return data;
    }

    public boolean isSuccess() {
        return success;
    }

    @JsonSerialize
    public class EmptyJsonResponse { }

    public static class ResponseMessage implements Serializable{
        private String message;
        ResponseMessage (String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }

    public static Builder newBuilder() {
        return new ResultResponse().new Builder();
    }

    public class Builder {

        private Builder() {
        }

        public Builder data(Object data) {
            ResultResponse.this.data = data;
            return this;
        }

        public Builder success() {
            ResultResponse.this.success = true;
            return this;
        }

        public Builder message(String message) {
            ResultResponse.this.data = new ResponseMessage(message);
            return this;
        }

        public ResultResponse build() {
            return ResultResponse.this;
        }
    }
}
