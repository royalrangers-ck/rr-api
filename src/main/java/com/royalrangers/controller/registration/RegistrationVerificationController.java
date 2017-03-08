package com.royalrangers.controller.registration;

import com.royalrangers.bean.Email;
import com.royalrangers.bean.ResultResponse;
import com.royalrangers.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class RegistrationVerificationController {

    @Autowired
    private UserService userService;

    @PostMapping("/registration/check/email")
    public ResponseEntity checkEmail(@RequestBody Email email) {

        String mail = email.getEmail();

        log.info("Checking email: " + mail);

        if (userService.isEmailExist(mail)) {
            return ResponseEntity.ok(new ResultResponse(false, "User with such an email already exists!"));
        }

        return ResponseEntity.ok(new ResultResponse(true));

    }
}
