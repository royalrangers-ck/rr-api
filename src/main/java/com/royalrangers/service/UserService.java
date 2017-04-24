package com.royalrangers.service;

import com.dropbox.core.DbxException;
import com.royalrangers.dto.user.UserRegistrationDto;
import com.royalrangers.dto.user.UserUpdateDto;
import com.royalrangers.enums.AuthorityName;
import com.royalrangers.enums.ImageType;
import com.royalrangers.enums.UserAgeGroup;
import com.royalrangers.enums.UserStatus;
import com.royalrangers.exception.UserRepositoryException;
import com.royalrangers.model.Authority;
import com.royalrangers.model.User;
import com.royalrangers.repository.*;
import com.royalrangers.utils.security.JwtUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.*;

@Service
public class UserService {

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
        if (Objects.equals(userDto.getStatus(), UserStatus.TEACHER)) {
            grantAuthority(user, AuthorityName.ROLE_USER, AuthorityName.ROLE_ADMIN);
        } else {
            grantAuthority(user, AuthorityName.ROLE_USER);
        }
        return user;
    }

    public Boolean isEmailExist(String email) {
        return (userRepository.findByEmail(email) != null);
    }

    public String getConfirmRegistrationLink(User user) throws UnknownHostException {

        String token = verificationTokenService.generateToken(user);
        String confirmRegistrationUrl =
                "http://" + InetAddress.getLocalHost().getHostAddress()
                        + "/landing/#/registration/confirm?token=" + token;
        return confirmRegistrationUrl;
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

    public List<User> getUsersForApprove(Long platoonId) {
        return getUsersToApproveByPlatoonID(platoonId);
    }

    public Long getAuthenticatedUserId() {
        JwtUser user = (JwtUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user.getId();
    }

    public String getAuthenticatedUserEmail() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    public void approveUsers(List<Long> ids) {
        ids.forEach(id -> {
            User user = userRepository.findOne(id);
            user.setApproved(true);
            user.setEnabled(true);
            userRepository.save(user);
            emailService.sendEmail(user, "Registration accepted", "approved.inline.html", "");
        });
    }

    public void rejectUsers(List<Long> ids) {
        ids.forEach(id -> {
            User user = userRepository.findOne(id);
            user.setEnabled(false);
            user.setConfirmed(false);
            userRepository.save(user);
            emailService.sendEmail(user, "Registration rejected", "rejected.inline.html", "");
        });
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User getUserById(Long id) throws DataAccessException {
        if (!userRepository.exists(id)) {
            throw new UserRepositoryException("Not found user with id " + id);
        }
        return userRepository.findOne(id);
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
        if (!userRepository.exists(id)) {
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

    public List<User> getUsersByPlatoon() {
        String email = getAuthenticatedUserEmail();
        User user = userRepository.findByEmail(email);
        List<User> users = userRepository.findUsersByApprovedTrueAndPlatoon_Id(user.getPlatoon().getId());
        return users;
    }

    public void setUserAvatarUrl(String avatarUrl) throws DbxException {
        String email = getAuthenticatedUserEmail();
        User user = userRepository.findByEmail(email);

        if (user.getAvatarUrl() != null) {
            dropboxService.deleteImage(user.getAvatarUrl(), ImageType.USER_AVATAR);
        }

        user.setAvatarUrl(avatarUrl);
        userRepository.save(user);
    }
}

