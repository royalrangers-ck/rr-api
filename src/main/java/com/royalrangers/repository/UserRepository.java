package com.royalrangers.repository;

import com.royalrangers.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

    User findByUsernameOrEmail(String username, String email);

    int countByEmail(String email);
}
