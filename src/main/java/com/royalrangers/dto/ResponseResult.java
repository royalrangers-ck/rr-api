package com.royalrangers.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.royalrangers.model.Views;
import lombok.Getter;

@Getter
@JsonSerialize
public class ResponseResult {

    @JsonView(Views.Common.class)
    private Object data;

    @JsonView(Views.Common.class)
    private boolean success;

    public ResponseResult(boolean success, Object data) {
        this.data = data;
        this.success = success;
    }
}