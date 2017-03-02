package com.royalrangers.registration.service;

import com.royalrangers.model.User;

public interface UserService {
    void save(User user);
    User findByUserEmail(String email);
}
