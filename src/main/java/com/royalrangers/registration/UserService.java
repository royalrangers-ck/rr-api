package com.royalrangers.registration;


import com.royalrangers.model.User;
import com.royalrangers.security.repository.UserRepository;

import java.util.List;

public interface UserService {
    void save(User user);
    User createUserFromUserform(UserForm userForm);
    User findByUsername(String username);
    User findByUserEmail(String email);
}

