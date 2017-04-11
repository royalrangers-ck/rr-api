package com.royalrangers.service;

import com.royalrangers.enums.Messages;
import com.royalrangers.model.User;
import com.royalrangers.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private UserRepository userRepository;

    private Messages messages;

    public Messages checkLoginInformation(String email) {

        User user = userRepository.findByEmail(email);
        if (user == null) {
            return messages.NOT_EXIST;
        } else if (!user.getConfirmed()) {
            return messages.NOT_CONFIRMED;
        } else if (user.getConfirmed() && !user.getApproved()) {
            return messages.NOT_APPROVED;
        } else if (!user.getEnabled()) {
            return messages.DENIED;
        }
        return null;
    }
}
