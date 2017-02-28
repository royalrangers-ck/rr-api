package com.royalrangers.registration;

import com.royalrangers.model.User;
import com.royalrangers.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    public void save(User user) {
        userRepository.save(user);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsernameOrEmail(username, username);
    }

    public User findByUserEmail(String email) {
        return userRepository.findByUsernameOrEmail(email,email);
    }
}

