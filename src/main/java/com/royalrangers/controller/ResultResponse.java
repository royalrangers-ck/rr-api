package com.royalrangers.controller;

import lombok.Getter;

import java.io.Serializable;

@Getter
public class ResultResponse implements Serializable {
    private boolean status;
    private String message;

    public ResultResponse(Boolean status, String message) {
        this.status = status;
        this.message = message;
     }
}
