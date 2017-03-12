package com.royalrangers.service;

import com.royalrangers.model.*;
import com.royalrangers.bean.UserBean;
import com.royalrangers.repository.AuthorityRepository;
import com.royalrangers.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthorityRepository authorityRepository;

    public void grantAuthority(User user, AuthorityName... roles) {
        Set<Authority> authoritySet = new HashSet<>();
        for (AuthorityName role : roles) {
            Authority authority = authorityRepository.findByName(role);
            authoritySet.add(authority);
        }
        user.setAuthorities(authoritySet);
    }

    public User createUserFromUserForm(UserBean userBean) {
        User user = new User();
        user.setEmail(userBean.getEmail());
        user.setFirstName(userBean.getFirstName());
        user.setLastName(userBean.getLastName());
        user.setPassword(passwordEncoder.encode(userBean.getPassword()));
        user.setEmail(userBean.getEmail());
        user.setEnabled(false);
        user.setLastPasswordResetDate(new Date(System.currentTimeMillis()));
        user.setGender(userBean.getGender());
        user.setTelephoneNumber(userBean.getTelephonNumber());
        user.setBirthDate(userBean.getBirthDate());
        user.setCountry(new Country(userBean.getCountry()));
        user.setCity(new City(user.getCountry(), userBean.getCity()));
        user.setGroup(new Group(user.getCity(), userBean.getGroup()));
        user.setPlatoon(new Platoon(user.getGroup(), userBean.getPlatoon()));
        user.setSection(new Section(user.getPlatoon(), userBean.getSection()));
        if (Objects.equals(userBean.getStatus(), "teacher")) {
            grantAuthority(user, AuthorityName.ROLE_USER, AuthorityName.ROLE_ADMIN);
        } else {
            grantAuthority(user, AuthorityName.ROLE_USER);
        }

        return user;
    }

    public Boolean isEmailExist(String email) {
        return (userRepository.findByEmail(email) != null);
    }

    public void saveUser(UserBean userBean, User user) {
        String password = passwordEncoder.encode(userBean.getPassword());
        userBean.setPassword(password);
        userRepository.save(user);
    }

    public User findByUserEmail(String email) {
        return userRepository.findByEmail(email);
    }
}

