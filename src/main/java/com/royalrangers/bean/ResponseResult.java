package com.royalrangers.bean;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;

@Getter
@JsonSerialize
public class ResponseResult {

    private Object data;
    private boolean success;

    public ResponseResult(boolean success, Object data) {
        this.data = data;
        this.success = success;
    }
}