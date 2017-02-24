package com.royalrangers.security;

import lombok.Getter;

import java.io.Serializable;

@Getter
public class JwtAuthenticationResponse implements Serializable {

    private final String token;

    public JwtAuthenticationResponse(String token) {
        this.token = token;
    }
}
