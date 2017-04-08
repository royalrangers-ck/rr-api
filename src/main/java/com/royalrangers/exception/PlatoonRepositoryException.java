package com.royalrangers.exception;

import org.springframework.dao.DataAccessException;

public class PlatoonRepositoryException extends DataAccessException {
    public PlatoonRepositoryException(String msg) {
        super(msg);
    }
}
