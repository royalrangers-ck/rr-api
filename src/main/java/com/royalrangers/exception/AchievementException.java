package com.royalrangers.exception;

import org.springframework.dao.DataAccessException;

public class AchievementException extends DataAccessException {

    public AchievementException(String msg) {
        super(msg);
    }
}
