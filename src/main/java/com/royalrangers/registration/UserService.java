package com.royalrangers.registration;

import com.royalrangers.model.User;

public interface UserService {

    void save(User user);

    User createUserFromUserform(UserForm userForm);

    User findByUsername(String username);

    User findByUserEmail(String email);
}

