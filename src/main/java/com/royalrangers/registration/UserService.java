package com.royalrangers.registration;


import com.royalrangers.model.User;
import com.royalrangers.security.repository.UserRepository;

import java.util.List;

public interface UserService {
    public void save(User user);
    User findByUsername(String username);
    User findByUserEmail(String email);
}

