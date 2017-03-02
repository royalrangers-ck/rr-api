package com.royalrangers.registration.service;

import com.royalrangers.model.User;
import com.royalrangers.registration.service.UserService;
import com.royalrangers.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    public void save(User user) {
        userRepository.save(user);
    }

    public User findByUserEmail(String email) {
        return userRepository.findByUsernameOrEmail(email,email);
    }
}

