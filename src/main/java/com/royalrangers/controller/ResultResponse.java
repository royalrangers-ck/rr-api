package com.royalrangers.controller;

import lombok.Getter;

import java.io.Serializable;

@Getter
public class ResultResponse implements Serializable {
    private String result;

    public ResultResponse(String result) {
        this.result = result;
     }
}
