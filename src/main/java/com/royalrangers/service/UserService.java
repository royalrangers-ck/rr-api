package com.royalrangers.service;

import com.dropbox.core.DbxException;
import com.royalrangers.dto.achievement.UserAchievementBean;
import com.royalrangers.dto.user.UserDto;
import com.royalrangers.dto.user.UserRegistrationDto;
import com.royalrangers.enums.AuthorityName;
import com.royalrangers.enums.Status;
import com.royalrangers.enums.UserAgeGroup;
import com.royalrangers.exception.UserRepositoryException;
import com.royalrangers.model.Authority;
import com.royalrangers.model.User;
import com.royalrangers.repository.*;
import com.royalrangers.utils.security.JwtUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@PropertySource("classpath:emailproperties.yml")
public class UserService {

    @Value("${confirmRegistrationUrl}")
    private String confirmRegistrationUrl;

    @Autowired
    private DropboxService dropboxService;

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

    public User createUserFromUserForm(UserRegistrationDto userDto) {
        User user = new User();
        user.setCreateDate(user.getCreateDate());
        user.setUpdateDate(user.getUpdateDate());
        user.setEmail(userDto.getEmail());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setEmail(userDto.getEmail());
        user.setEnabled(false);
        user.setConfirmed(false);
        user.setApproved(false);
        user.setUserAgeGroup(determineUserAgeGroup(calculateUserAge(userDto.getBirthDate())));
        user.setLastPasswordResetDate(new Date(System.currentTimeMillis()));
        user.setGender(userDto.getGender());
        user.setTelephoneNumber(userDto.getTelephoneNumber());
        user.setBirthDate(userDto.getBirthDate());
        user.setCountry(countryRepository.findOne(userDto.getCountryId()));
        user.setCity(cityRepository.findOne(userDto.getCityId()));
        user.setGroup(groupRepository.findOne(userDto.getGroupId()));
        user.setPlatoon(platoonRepository.findOne(userDto.getPlatoonId()));
        user.setSection(sectionRepository.findOne(userDto.getSectionId()));
        if (Objects.equals(userDto.getStatus(), Status.TEACHER)) {
            grantAuthority(user, AuthorityName.ROLE_USER, AuthorityName.ROLE_ADMIN);
        } else {
            grantAuthority(user, AuthorityName.ROLE_USER);
        }
        return user;
    }

    public static UserDto buildUserBean(User user) {
        UserDto userDto = new UserDto();
        userDto.setCreateDate(user.getCreateDate());
        userDto.setUpdateDate(user.getUpdateDate());
        userDto.setId(user.getId());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setGender(user.getGender());
        userDto.setEnabled(user.getEnabled());
        userDto.setConfirmed(user.getConfirmed());
        userDto.setApproved(user.getApproved());
        userDto.setBirthDate(user.getBirthDate());
        userDto.setTelephoneNumber(user.getTelephoneNumber());
        userDto.setUserAgeGroup(user.getUserAgeGroup());
        userDto.setCountryId(user.getCountry().getId());
        userDto.setCityId(user.getCity().getId());
        userDto.setGroupId(user.getGroup().getId());
        userDto.setPlatoonId(user.getPlatoon().getId());
        userDto.setSectionId(user.getSection().getId());
        return userDto;
    }

    public static UserAchievementBean buildUserAchievementBean(User user){
        UserAchievementBean userBean = new UserAchievementBean();
        userBean.setId(user.getId());
        userBean.setEmail(user.getEmail());
        userBean.setFirstName(user.getFirstName());
        userBean.setLastName(user.getLastName());
        userBean.setPlatoonId(user.getPlatoon().getId());
        return  userBean;
    }

    public Boolean isEmailExist(String email) {
        return (userRepository.findByEmail(email) != null);
    }

    public String getConfirmRegistrationLink(User user) {
        String token = verificationTokenService.generateToken(user);
        return confirmRegistrationUrl + "/api/registration/confirm?token=" + token;
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

    public List<User> getUsersToApproveByPlatoonID(Long platoonId) {
        return userRepository.findAllByConfirmedTrueAndApprovedFalseAndPlatoonId(platoonId);
    }

    public List<UserDto> getUsersForApprove(Long platoonId) {
        List<User> listUsersToApprove = getUsersToApproveByPlatoonID(platoonId);
        List<UserDto> listUsersBeanToApprove = new ArrayList<>();
        for (User user : listUsersToApprove) {
            UserDto userDto = buildUserBean(user);
            listUsersBeanToApprove.add(userDto);
        }
        return listUsersBeanToApprove;
    }

    public User getUserById(Long id){
        return userRepository.findOne(id);
    }

    public Long getAuthenticatedUserId(){
        JwtUser user = (JwtUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user.getId();
    }

    public void approveUsers(List<Long> ids) {
        ids.forEach(id -> {
            User user = userRepository.findOne(id);
            user.setApproved(true);
            user.setEnabled(true);
            userRepository.save(user);
        });
    }

    public void rejectUsers(List<Long> ids) {
        ids.forEach(id -> {
            User user = userRepository.findOne(id);
            user.setEnabled(false);
            user.setConfirmed(false);
            userRepository.save(user);
        });
    }

    public boolean isAuthenticatedUserHasRole(String role) {
        Collection<GrantedAuthority> authorities = (Collection<GrantedAuthority>)
                SecurityContextHolder.getContext().getAuthentication().getAuthorities();

        return authorities.stream().anyMatch(auth -> auth.getAuthority().equals(role));
    }

    public String getAuthenticatedUserEmail() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    public void updateUserByEmail(String email, UserDto update) {

        User user = userRepository.findByEmail(email);

        if(!isAuthenticatedUserHasRole("ROLE_ADMIN")) {
            updateUserItself(user, update);
        } else {
            updateUserByAdmin(user, update);
        }
    }

    public void updateUserById(Long id, UserDto update) {
        if(!userRepository.exists(id)) {
            throw new UserRepositoryException("Not found user with id " + id);
        }
        User user = userRepository.findOne(id);
        updateUserByAdmin(user, update);
    }

    public void updateUserByAdmin(User user, UserDto update) {
        user.setCreateDate(update.getCreateDate());
        user.setUpdateDate(new Date());
        user.setFirstName(update.getFirstName());
        user.setLastName(update.getLastName());
        user.setGender(update.getGender());
        user.setTelephoneNumber(update.getTelephoneNumber());
        user.setBirthDate(update.getBirthDate());
        user.setUserAgeGroup(update.getUserAgeGroup());
        user.setUserRank(update.getUserRank());
        user.setCountry(countryRepository.findOne(update.getCountryId()));
        user.setCity(cityRepository.findOne(update.getCityId()));
        user.setGroup(groupRepository.findOne(update.getGroupId()));
        user.setPlatoon(platoonRepository.findOne(update.getPlatoonId()));
        user.setSection(sectionRepository.findOne(update.getSectionId()));

        userRepository.save(user);
    }

    private void updateUserItself(User user, UserDto update) {
        user.setCreateDate(update.getCreateDate());
        user.setUpdateDate(new Date());
        user.setTelephoneNumber(update.getTelephoneNumber());
        user.setUserAgeGroup(update.getUserAgeGroup());
        user.setUserRank(update.getUserRank());
        user.setCountry(countryRepository.findOne(update.getCountryId()));
        user.setCity(cityRepository.findOne(update.getCityId()));
        user.setGroup(groupRepository.findOne(update.getGroupId()));
        user.setPlatoon(platoonRepository.findOne(update.getPlatoonId()));
        user.setSection(sectionRepository.findOne(update.getSectionId()));
    }

    public void setUserAvatarUrl(String avatarUrl) throws DbxException {
        String email = getAuthenticatedUserEmail();
        User user = userRepository.findByEmail(email);

        if (user.getAvatarUrl() != null) {
            dropboxService.deleteAvatar(user.getAvatarUrl());
        }

        user.setAvatarUrl(avatarUrl);
        userRepository.save(user);
    }
}

