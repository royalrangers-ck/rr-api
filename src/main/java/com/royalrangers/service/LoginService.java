package com.royalrangers.service;

import com.royalrangers.bean.ResponseResult;
import com.royalrangers.enums.Messages;
import com.royalrangers.model.User;
import com.royalrangers.utils.ResponseBuilder;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private Messages messages;

    public ResponseResult loginInformation(User user) {
        if (!user.getConfirmed()) {
            return ResponseBuilder.fail(messages.NOT_CONFIRMED.getMessage());
        } else if (user.getConfirmed() && !user.getApproved()) {
            return ResponseBuilder.fail(messages.NOT_APPROVED.getMessage());
        } else if (!user.getEnabled()) {
            return ResponseBuilder.fail(messages.DENIED.getMessage());
        }
        return null;
    }
}
