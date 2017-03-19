package com.royalrangers.exception;

import org.springframework.dao.DataAccessException;

public class UserRepositoryException extends DataAccessException {

    public UserRepositoryException(String msg) {
        super(msg);
    }
}
