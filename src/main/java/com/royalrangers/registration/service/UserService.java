package com.royalrangers.registration.service;

import com.royalrangers.model.Authority;
import com.royalrangers.model.AuthorityName;
import com.royalrangers.model.User;
import com.royalrangers.registration.pojo.UserBean;
import com.royalrangers.registration.validator.Validator;
import com.royalrangers.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private Validator userValidator;

    void grantAuthority(User user, AuthorityName ... role){
        List<Authority> authorityList = new ArrayList<>();
        Authority authority = new Authority();
        for(AuthorityName authorityName: role) {
            authority.setName(authorityName);
            authorityList.add(authority);
        }
        user.setAuthorities(authorityList);
    }

    public User convertPojoToEntity(UserBean userBean){
        User user = new User();
        user.setUsername(userBean.getUsername());
        user.setFirstname(userBean.getFirstname());
        user.setLastname(userBean.getLastname());
        user.setPassword(userBean.getPassword());
        user.setEmail(userBean.getEmail());
        user.setGender(userBean.getGender());
        user.setStatus(userBean.getStatus());
        if (userBean.getStatus()=="teacher")
            grantAuthority(user, AuthorityName.ROLE_USER, AuthorityName.ROLE_ADMIN);
        else grantAuthority(user,AuthorityName.ROLE_USER);

        return user;
    }

    public ResponseEntity<String> validateAndSaveUser(UserBean userBean, User user) {
        if (!userValidator.validate(userBean))
            return new ResponseEntity<>("User with this email already exists", HttpStatus.CONFLICT);

        String password = passwordEncoder.encode(userBean.getPassword());
        userBean.setPassword(password);
        userRepository.save(user);
        return new ResponseEntity<>("User created successfully", HttpStatus.OK);
    }

    public User findByUserEmail(String email) {
        return userRepository.findByEmail(email);
    }
}

