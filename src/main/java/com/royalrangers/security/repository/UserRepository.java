package com.royalrangers.security.repository;

import com.royalrangers.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    int countByEmail(String email);
}
