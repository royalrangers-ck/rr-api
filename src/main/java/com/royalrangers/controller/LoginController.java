package com.royalrangers.controller;

import com.royalrangers.bean.ResponseResult;
import com.royalrangers.model.User;
import com.royalrangers.repository.UserRepository;
import com.royalrangers.utils.ResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {
    @Autowired
    UserRepository userRepository;
    User user;
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseResult login( @RequestParam String email) {
        user = userRepository.findByEmail(email);
        if(user.getConfirmed() == false)
        return ResponseBuilder.fail("Your email is not confirmed");
        else if(user.getConfirmed() == true && user.getApproved() == false)
        return ResponseBuilder.fail("You have not been approved by admin yet");
        else if(user.getEnabled()==false)
        return ResponseBuilder.fail("You have been denied by an admin");
        return ResponseBuilder.success("You have been approved");
    }
}
