package com.royalrangers.exception;

import org.springframework.dao.DataAccessException;

public class EntryAlreadyExistsException extends DataAccessException {
    public EntryAlreadyExistsException(String msg) {
        super(msg);
    }
}
