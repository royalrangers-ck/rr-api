package com.royalrangers.service;

import com.royalrangers.enums.ErrorMessages;
import com.royalrangers.model.User;
import com.royalrangers.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private UserRepository userRepository;

    private ErrorMessages messages;

    public ErrorMessages checkLoginInformation(String email) {
        User user;
        try {
            user = userRepository.findByEmail(email);
            if (!user.getConfirmed()) {
                return messages.NOT_CONFIRMED;
            } else if (user.getConfirmed() && !user.getApproved()) {
                return messages.NOT_APPROVED;
            } else if (!user.getEnabled()) {
                return messages.DENIED;
            }
        } catch (NullPointerException e) {
            return messages.NOT_EXIST;
        }
        return null;
    }
}
