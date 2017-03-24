package com.royalrangers.service;

import com.royalrangers.bean.ResponseResult;
import com.royalrangers.enums.Messages;
import com.royalrangers.model.User;
import com.royalrangers.utils.ResponseBuilder;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    public String getMessage(Messages messages){

        switch(messages) {
            case NOT_APPROVED:
                return "Your email is not confirmed";
            case NOT_CONFIRMED:
                return "You have not been approved by admin yet";
            case DENIED:
                return "You have been denied by an admin";
        }
        return null;
    }
    public ResponseResult loginInformation(User user) {

        if (user.getConfirmed() == false) {
            return ResponseBuilder.fail(getMessage(Messages.NOT_APPROVED));
        } else if (user.getConfirmed() == true && user.getApproved() == false) {
            return ResponseBuilder.fail(getMessage(Messages.NOT_CONFIRMED));
        } else if (user.getEnabled() == false) {
            return ResponseBuilder.fail(getMessage(Messages.DENIED));
        }
        return null;
    }
}
