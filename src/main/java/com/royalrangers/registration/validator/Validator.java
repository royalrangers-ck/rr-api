package com.royalrangers.registration.validator;

import com.royalrangers.model.User;
import com.royalrangers.registration.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Validator {
    @Autowired
    private UserService userService;

    public Boolean validate(User user) {

        if (userService.findByUserEmail(user.getEmail()) != null)
            return false;
        return true;
    }
}

