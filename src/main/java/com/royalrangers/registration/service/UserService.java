package com.royalrangers.registration.service;

import com.royalrangers.model.User;
import com.royalrangers.registration.bean.UserForm;

public interface UserService {

    void save(User user);

    User createUserFromUserform(UserForm userForm);

    User findByUsername(String username);

    User findByUserEmail(String email);
}

