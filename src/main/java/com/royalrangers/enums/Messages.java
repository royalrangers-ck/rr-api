package com.royalrangers.enums;

import lombok.Getter;

@Getter
public enum Messages {
    NOT_EXIST("This email is not exist"),
    NOT_CONFIRMED("Your email is not confirmed"),
    NOT_APPROVED("You have not been approved by admin yet"),
    DENIED("You have been denied by an admin");

    private String message;

    Messages(String message) {
        this.message = message;
    }
}
