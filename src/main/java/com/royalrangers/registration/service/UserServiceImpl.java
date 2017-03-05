package com.royalrangers.registration.service;

import com.royalrangers.model.User;
import com.royalrangers.registration.bean.UserForm;
import com.royalrangers.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void save(User user) {
        userRepository.save(user);
    }

    public User createUserFromUserform(UserForm userForm){

        User user = new User();
        user.setUsername(userForm.getUsername());
        user.setFirstName(userForm.getFirstname());
        user.setLastName(userForm.getLastname());
        user.setEmail(userForm.getEmail());
        user.setPassword(passwordEncoder.encode(userForm.getPassword()));

        return user;
    }

    public User findByUsername(String username) {
        return userRepository.findByUsernameOrEmail(username, username);
    }

    public User findByUserEmail(String email) {
        return userRepository.findByUsernameOrEmail(email,email);
    }
}

