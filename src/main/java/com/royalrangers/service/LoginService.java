package com.royalrangers.service;

import com.royalrangers.enums.LoginMessages;
import com.royalrangers.model.User;
import com.royalrangers.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private UserRepository userRepository;

    public LoginMessages checkLoginInformation(String email) {

        User user = userRepository.findByEmail(email);
        if (user == null) {
            return LoginMessages.NOT_EXIST;
        } else if (!user.getConfirmed()) {
            return LoginMessages.NOT_CONFIRMED;
        } else if (user.getConfirmed() && !user.getApproved()) {
            return LoginMessages.NOT_APPROVED;
        } else if (!user.getEnabled()) {
            return LoginMessages.DENIED;
        }

        return null;
    }
}
