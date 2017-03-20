package com.royalrangers.service;

import com.royalrangers.bean.UserBean;
import com.royalrangers.enums.AuthorityName;
import com.royalrangers.enums.Status;
import com.royalrangers.enums.UserAgeGroup;
import com.royalrangers.model.*;
import com.royalrangers.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@PropertySource("classpath:emailproperties.yml")
public class UserService {

    @Value("${confirmRegistrationUrl}")
    private String confirmRegistrationUrl;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private PlatoonRepository platoonRepository;

    @Autowired
    private SectionRepository sectionRepository;

    @Autowired
    private VerificationTokenService verificationTokenService;

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
        user.setUserAgeGroup(determineUserAgeGroup(calculateUserAge(userBean.getBirthDate())));
        user.setLastPasswordResetDate(new Date(System.currentTimeMillis()));
        user.setGender(userBean.getGender());
        user.setTelephoneNumber(userBean.getPhoneNumber());
        user.setBirthDate(userBean.getBirthDate());
        user.setCountry(countryRepository.findOne(userBean.getCountryId()));
        user.setCity(cityRepository.findOne(userBean.getCityId()));
        user.setGroup(groupRepository.findOne(userBean.getGroupId()));
        user.setPlatoon(platoonRepository.findOne(userBean.getPlatoonId()));
        user.setSection(sectionRepository.findOne(userBean.getSectionId()));
        if (Objects.equals(userBean.getStatus(), Status.TEACHER)) {
            grantAuthority(user, AuthorityName.ROLE_USER, AuthorityName.ROLE_ADMIN);
        } else {
            grantAuthority(user, AuthorityName.ROLE_USER);
        }
        return user;
    }

    public static UserBean buildUserBean(User user){
        UserBean userBean = new UserBean();
        userBean.setId(user.getId());
        userBean.setEmail(user.getEmail());
        userBean.setPassword(user.getPassword());
        userBean.setFirstName(user.getFirstName());
        userBean.setLastName(user.getLastName());
        userBean.setGender(user.getGender());
        userBean.setEnabled(user.getEnabled());
        userBean.setConfirmed(user.getConfirmed());
        userBean.setApproved(user.getApproved());
        userBean.setBirthDate(user.getBirthDate());
        userBean.setPhoneNumber(user.getTelephoneNumber());
        userBean.setConfirmed(user.getConfirmed());
        userBean.setEnabled(user.getEnabled());
        userBean.setApproved(user.getApproved());
        userBean.setUserAgeGroup(user.getUserAgeGroup());
        userBean.setCountryName(user.getCountry().getName());
        userBean.setCityName(user.getCity().getName());
        userBean.setGroupName(user.getGroup().getName());
        userBean.setPlatoonName(user.getPlatoon().getName());
        userBean.setSectionName(user.getSection().getName());
        return  userBean;
    }

    public Boolean isEmailExist(String email) {
        return (userRepository.findByEmail(email) != null);
    }

    public String getConfirmRegistrationLink(User user) {
        String token = verificationTokenService.generateToken(user);
        return confirmRegistrationUrl + "/registration/confirm?token=" + token;
    }

    public int calculateUserAge(Long birthdate) {
        Calendar cal = Calendar.getInstance();
        double userAge = (cal.getTime().getTime() - birthdate) * 3.170979E-11;
        return (int) userAge;
    }

    public UserAgeGroup determineUserAgeGroup(int userAge) {
        UserAgeGroup rank = UserAgeGroup.BEGINNER;
        if (userAge >= 5 && userAge <= 7)
            return UserAgeGroup.BEGINNER;
        if (userAge >= 8 && userAge <= 10)
            return UserAgeGroup.PIONEER;
        if (userAge >= 11 && userAge <= 13)
            return UserAgeGroup.PATHFINDER;
        if (userAge >= 14 && userAge <= 16)
            return UserAgeGroup.RANGER;
        if (userAge >= 17)
            return UserAgeGroup.ADULT;
        return rank;
    }

    public List<User> getUsersToApproveByPlatoonID(Long id){
        return userRepository.findAllByConfirmedTrueAndApprovedFalseAndPlatoonId(id);
    }

    public List<UserBean> getListUserToApprove(Long id){
        List<User> listUsersToApprove = getUsersToApproveByPlatoonID(id);
        List<UserBean> listUsersBeanToApprove = new ArrayList<>();
        for(User user: listUsersToApprove){
            UserBean userBean = buildUserBean(user);
            listUsersBeanToApprove.add(userBean);
        }
        return listUsersBeanToApprove;
    }

    public void setApproveToUser(ArrayList<Long> listId){
        for(Long id: listId){
            User user = userRepository.findOne(id);
            user.setApproved(true);
            user.setEnabled(true);
            userRepository.save(user);
        }
    }
}

