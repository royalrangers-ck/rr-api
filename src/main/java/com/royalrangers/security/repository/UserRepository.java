package com.royalrangers.security.repository;

import com.royalrangers.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
   
    User findByUsername(String username);
    User findByEmail(String email);
   
    User findByUsernameOrEmail(String username, String email);

    int countByEmail(String email);
  
}
