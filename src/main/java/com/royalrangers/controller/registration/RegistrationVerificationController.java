package com.royalrangers.controller.registration;

import com.royalrangers.bean.Email;
import com.royalrangers.bean.ResponseResult;
import com.royalrangers.service.UserService;
import com.royalrangers.utils.ResponseBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class RegistrationVerificationController {

    @Autowired
    private UserService userService;

    @PostMapping("/registration/check/email")
    public ResponseResult checkEmail(@RequestBody Email email) {

        String mail = email.getEmail();

        log.info("Checking email: " + mail);

        if (userService.isEmailExist(mail)) {
            return ResponseBuilder.fail("User with such an email already exists!");
        }

        return ResponseBuilder.success();

    }
}
