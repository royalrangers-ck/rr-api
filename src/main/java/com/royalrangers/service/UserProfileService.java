package com.royalrangers.service;

import com.royalrangers.bean.UserProfile;
import com.royalrangers.model.User;
import com.royalrangers.repository.UserRepository;
import com.royalrangers.utils.UserProfileFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserProfileService {

    @Autowired
    UserRepository userRepository;

    public UserProfile getUserDetailByEmail(String email) {
        User user = userRepository.findByEmail(email);
        return UserProfileFactory.create(user);
    }
}
