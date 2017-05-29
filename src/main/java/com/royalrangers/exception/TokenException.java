package com.royalrangers.exception;


import org.springframework.dao.DataAccessException;

public class TokenException extends DataAccessException {
    public TokenException(String msg){
        super(msg);
    }
}
