package com.royalrangers.controller.registration;

import com.royalrangers.bean.Email;
import com.royalrangers.bean.ResultResponse;
import com.royalrangers.repository.UserRepository;

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
    private UserRepository userRepository;

    @PostMapping("/registration/check/email")
    public ResponseEntity checkEmail(@RequestBody Email email) {

        String mail = email.getEmail();

        log.info("Checking email: " + mail);

        if (userRepository.countByEmail(mail) != 0) {
            return ResponseEntity.ok(new ResultResponse(false, "User with such an email already exists!"));
        }

        return ResponseEntity.ok(new ResultResponse(true));

    }
}
