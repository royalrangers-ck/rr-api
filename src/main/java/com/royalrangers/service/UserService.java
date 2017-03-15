package com.royalrangers.service;

import com.royalrangers.bean.UserBean;
import com.royalrangers.enums.Status;
import com.royalrangers.enums.UserRank;
import com.royalrangers.model.*;
import com.royalrangers.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    CountryRepository countryRepository;
    @Autowired
    CityRepository cityRepository;
    @Autowired
    GroupRepository groupRepository;
    @Autowired
    PlatoonRepository platoonRepository;
    @Autowired
    SectionRepository sectionRepository;
    @Autowired
    VerificationTokenRepository tokenRepository;
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
        user.setConfirmed(false);
        user.setApproved(false);
        user.setUserRank(determineUserRank(calculateUserAge(userBean.getBirthDate())));
        user.setLastPasswordResetDate(new Date(System.currentTimeMillis()));
        user.setGender(userBean.getGender());
        user.setTelephoneNumber(userBean.getTelephonNumber());
        user.setBirthDate(userBean.getBirthDate());
        user.setCountry(countryRepository.findOne(userBean.getCountryID()));
        user.setCity(cityRepository.findOne(userBean.getCityID()));
        user.setGroup(groupRepository.findOne(userBean.getGroupID()));
        user.setPlatoon(platoonRepository.findOne(userBean.getPlatoonID()));
        user.setSection(sectionRepository.findOne(userBean.getSectionID()));
        if (Objects.equals(userBean.getStatus(), Status.TEACHER)) {
            grantAuthority(user, AuthorityName.ROLE_USER, AuthorityName.ROLE_ADMIN);
        } else {
            grantAuthority(user, AuthorityName.ROLE_USER);
        }
        return user;
    }

    public Boolean isEmailExist(String email) {
        return (userRepository.findByEmail(email) != null);
    }

    public void createVerificationTokenForUser(final User user, final String token) {
        final VerificationToken myToken = new VerificationToken(token, user);
        tokenRepository.save(myToken);
    }

    public VerificationToken getVerificationToken(final String VerificationToken) {
        return tokenRepository.findByToken(VerificationToken);
    }

    public String getConfimRegistrationLink(User user) {
        final String token = UUID.randomUUID().toString();
        String confirmLink = "http://localhost:8080/registration/confirm?token=" + token;
        createVerificationTokenForUser(user, token);
        return confirmLink;
    }

    public int calculateUserAge(Date birthdate) {
        Calendar cal = Calendar.getInstance();
        double userAge = (cal.getTime().getTime() - birthdate.getTime()) * 3.170979E-11;
        return (int) userAge;
    }

    public UserRank determineUserRank(int userAge) {
        UserRank rank = UserRank.BEGINNER;
        if (userAge >= 5 && userAge <= 7)
            return UserRank.BEGINNER;
        if (userAge >= 8 && userAge <= 10)
            return UserRank.PIONEER;
        if (userAge >= 11 && userAge <= 13)
            return UserRank.PATHFINDER;
        if (userAge >= 14 && userAge <= 16)
            return UserRank.RANGER;
        if (userAge >= 17)
            return UserRank.ADULT;
        return rank;
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }
}

