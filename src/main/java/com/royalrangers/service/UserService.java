package com.royalrangers.service;

import com.dropbox.core.DbxException;
import com.royalrangers.dto.achievement.UserAchievementDto;
import com.royalrangers.dto.user.*;
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
import org.springframework.dao.DataAccessException;
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
    private EmailService emailService;

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
        user.setCreateDate(new Date());
        user.setUpdateDate(new Date());
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

    public static UserProfileDto buildUserProfile(User user) {
        UserProfileDto userProfile = new UserProfileDto();
        userProfile.setCreateDate(user.getCreateDate());
        userProfile.setUpdateDate(user.getUpdateDate());
        userProfile.setId(user.getId());
        userProfile.setEmail(user.getEmail());
        userProfile.setFirstName(user.getFirstName());
        userProfile.setLastName(user.getLastName());
        userProfile.setGender(user.getGender());
        userProfile.setBirthDate(user.getBirthDate());
        userProfile.setTelephoneNumber(user.getTelephoneNumber());
        userProfile.setUserAgeGroup(user.getUserAgeGroup());
        userProfile.setUserRank(user.getUserRank());
        userProfile.setCountryId(user.getCountry().getId());
        userProfile.setCityId(user.getCity().getId());
        userProfile.setGroupId(user.getGroup().getId());
        userProfile.setPlatoonId(user.getPlatoon().getId());
        userProfile.setSectionId(user.getSection().getId());
        userProfile.setAvatarUrl(user.getAvatarUrl());
        return userProfile;
    }

    public static UserAchievementDto buildUserAchievementBean(User user){
        UserAchievementDto userBean = new UserAchievementDto();
        userBean.setId(user.getId());
        userBean.setEmail(user.getEmail());
        userBean.setFirstName(user.getFirstName());
        userBean.setLastName(user.getLastName());
        userBean.setPlatoonId(user.getPlatoon().getId());
        userBean.setUserAvatarUrl(user.getAvatarUrl());
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

    public List<UserProfileDto> getUsersForApprove(Long platoonId) {
        List<User> listUsersToApprove = getUsersToApproveByPlatoonID(platoonId);
        List<UserProfileDto> listUsersBeanToApprove = new ArrayList<>();
        for (User user : listUsersToApprove) {
            UserProfileDto userProfile = buildUserProfile(user);
            listUsersBeanToApprove.add(userProfile);
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
            emailService.sendEmail(user, "Registration accepted", "approved.inline.html","");
        });
    }

    public void rejectUsers(List<Long> ids) {
        ids.forEach(id -> {
            User user = userRepository.findOne(id);
            user.setEnabled(false);
            user.setConfirmed(false);
            userRepository.save(user);
            emailService.sendEmail(user,"Registration rejected", "rejected.inline.html", "");
        });
    }

    public String getAuthenticatedUserEmail() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    public UserProfileDto getUserDetailByEmail(String email) {
        User user = userRepository.findByEmail(email);
        return buildUserProfile(user);
    }

    public UserProfileDto getUserDetailById(Long id) throws DataAccessException {
        if(!userRepository.exists(id)) {
            throw new UserRepositoryException("Not found user with id " + id);
        }
        User user = userRepository.findOne(id);
        return buildUserProfile(user);
    }

    public void updateUser(UserUpdateDto update) {
        User user = userRepository.findByEmail(getAuthenticatedUserEmail());

        user.setUpdateDate(new Date());
        user.setFirstName(update.getFirstName());
        user.setLastName(update.getLastName());
        user.setGender(update.getGender());
        user.setBirthDate(update.getBirthDate());
        user.setTelephoneNumber(update.getTelephoneNumber());
        user.setUserAgeGroup(update.getUserAgeGroup());
        user.setUserRank(update.getUserRank());
        user.setCountry(countryRepository.findOne(update.getCountryId()));
        user.setCity(cityRepository.findOne(update.getCityId()));
        user.setGroup(groupRepository.findOne(update.getGroupId()));
        user.setPlatoon(platoonRepository.findOne(update.getPlatoonId()));
        user.setSection(sectionRepository.findOne(update.getSectionId()));

        userRepository.save(user);
    }

    public void updateUserById(Long id, UserUpdateDto update) {
        if(!userRepository.exists(id)) {
            throw new UserRepositoryException("Not found user with id " + id);
        }

        User user = userRepository.findOne(id);

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

