package com.royalrangers.registration.service;

import com.royalrangers.model.*;
import com.royalrangers.registration.pojo.UserBean;
import com.royalrangers.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    void grantAuthority(User user, AuthorityName... roles) {
        List<Authority> authorityList = new ArrayList<>();
        Authority authority = new Authority();
        for (AuthorityName authorityName : roles) {
            authority.setName(authorityName);
            authorityList.add(authority);
        }
        user.setAuthorities(authorityList);
    }

    public User convertPojoToEntity(UserBean userBean) {
        User user = new User();
        user.setUsername(userBean.getUsername());
        user.setFirstname(userBean.getFirstname());
        user.setLastname(userBean.getLastname());
        user.setPassword(passwordEncoder.encode(userBean.getPassword()));
        user.setEmail(userBean.getEmail());
        user.setGender(userBean.getGender());
        user.setStatus(userBean.getStatus());
        user.setCountry(new Country(userBean.getCountry()));
        user.setCity(new City(user.getCountry(), userBean.getCity()));
        user.setGroup(new Group(user.getCity(), userBean.getGroup()));
        user.setPlatoon(new Platoon(user.getGroup(), userBean.getPlatoon()));
        user.setSection(new Section(user.getPlatoon(), userBean.getSection()));
        if (userBean.getStatus() == "teacher")
            grantAuthority(user, AuthorityName.ROLE_USER, AuthorityName.ROLE_ADMIN);
        else grantAuthority(user, AuthorityName.ROLE_USER);

        return user;
    }

    public Boolean validate(UserBean userBean) {
        User userByEmail = findByUserEmail(userBean.getEmail());
        return userByEmail == null;
    }

    public void SaveUser(UserBean userBean, User user) {
        String password = passwordEncoder.encode(userBean.getPassword());
        userBean.setPassword(password);
        userRepository.save(user);
    }

    public User findByUserEmail(String email) {
        return userRepository.findByEmail(email);
    }
}

