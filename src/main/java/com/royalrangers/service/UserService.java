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
import com.royalrangers.model.TempUser;
import com.royalrangers.model.User;
import com.royalrangers.model.VerificationToken;
import com.royalrangers.repository.*;
import com.royalrangers.utils.security.JwtUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.*;

@Slf4j
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

    @Autowired
    private TempUserRepository tempUserRepository;

    private void grantAuthority(User user, AuthorityName... roles) {
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
        user.setRejected(false);
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
        user.setUserRank(userDto.getUserRank());
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

    private int calculateUserAge(Long birthdate) {
        Calendar cal = Calendar.getInstance();
        double userAge = (cal.getTime().getTime() - birthdate) * 3.170979E-11;
        return (int) userAge;
    }

    private UserAgeGroup determineUserAgeGroup(int userAge) {
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

    public List<User> getUsersForApproveForSuperAdmin() {
        return userRepository.findAllByConfirmedTrueAndApprovedFalse();
    }

    public Long getAuthenticatedUserId() {
        JwtUser user = (JwtUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user.getId();
    }

    public User getAuthenticatedUser() {
        return userRepository.findByEmail(getAuthenticatedUserEmail());
    }

    public String getAuthenticatedUserEmail() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    public void approveUser(Long id) {
            User user = userRepository.findOne(id);
            user.setApproved(true);
            user.setEnabled(true);
            user.setRejected(false);
            userRepository.save(user);
            emailService.sendEmail(user, "Registration accepted", "approved.inline.html", "");
            log.info("User %s approved.", user.getEmail());
    }

    public void rejectUser(Long id) {
            User user = userRepository.findOne(id);
            user.setEnabled(false);
            user.setConfirmed(false);
            user.setRejected(true);
            userRepository.save(user);
            emailService.sendEmail(user, "Registration rejected", "rejected.inline.html", "");
            log.info("User %s rejected.", user.getEmail());
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

    public List<TempUser> getTempUsersByPlatoon(Long platoonId){
        return tempUserRepository.findByPlatoonId(platoonId);
    }

    public List<TempUser> getTempUsers(){
        return tempUserRepository.findAll();
    }

    public TempUser getTempUser(){
        TempUser tempUser = tempUserRepository.findByUserId(getAuthenticatedUserId());
        return tempUserRepository.findOne(tempUser.getId());
    }

    private TempUser createTempUser() {
        User user = getAuthenticatedUser();
        TempUser tempUser = new TempUser();

        tempUser.setUser(user);
        tempUser.setCreateDate(user.getCreateDate());
        tempUser.setUpdateDate(user.getUpdateDate());
        tempUser.setFirstName(user.getFirstName());
        tempUser.setLastName(user.getLastName());
        tempUser.setGender(user.getGender());
        tempUser.setTelephoneNumber(user.getTelephoneNumber());
        tempUser.setBirthDate(user.getBirthDate());
        tempUser.setUserAgeGroup(user.getUserAgeGroup());
        tempUser.setUserRank(user.getUserRank());
        tempUser.setCountry(user.getCountry());
        tempUser.setCity(user.getCity());
        tempUser.setGroup(user.getGroup());
        tempUser.setPlatoon(user.getPlatoon());
        tempUser.setSection(user.getSection());

        tempUserRepository.save(tempUser);
        return tempUser;
    }

    public TempUser getTempUserById(Long id) {
        return tempUserRepository.findOne(id);
    }

    public void updateTempUser(UserUpdateDto update ) {
        TempUser user = createTempUser();

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

        tempUserRepository.save(user);
    }

    public void updateUser(Long id, UserUpdateDto update) {
        TempUser tempUser = getTempUserById(id);
        User user = tempUser.getUser();

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
        tempUserRepository.delete(tempUser);
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
        return userRepository.findUsersByApprovedTrueAndPlatoon_Id(getAuthenticatedUser().getPlatoon().getId());
    }

    public void setUserAvatarUrl(String avatarUrl) throws DbxException {
        User user = getAuthenticatedUser();
        if (user.getAvatarUrl() != null) {
            dropboxService.deleteImage(user.getAvatarUrl(), ImageType.USER_AVATAR);
        }

        user.setAvatarUrl(avatarUrl);
        userRepository.save(user);
    }

    public void confirmUser(VerificationToken verificationToken) {
        User user = verificationToken.getUser();
        user.setConfirmed(true);
        emailService.sendEmail(getPlatoonAdmin(user), "New user needs approve", "newuserforapprove.inline.html", "");
        userRepository.save(user);
    }

    private User getPlatoonAdmin(User user) {
        Long platoonId = user.getPlatoon().getId();
        List<User> usersByPlatoon = userRepository.findAllByPlatoonId(platoonId);
        Optional<User> admin = usersByPlatoon.stream()
                .filter(element -> user.getAuthorities()
                        .contains(AuthorityName.ROLE_ADMIN))
                .findFirst();
        if (!admin.isPresent())
            throw new UserRepositoryException("Admin not found in platoon " + platoonId);

        return admin.get();
    }

    public void resendConfirmation(String email) throws UserRepositoryException, UnknownHostException {
        User user = userRepository.findByEmail(email);
        if (!isEmailExist(email)) {
            throw new UserRepositoryException("User with this email is not exist.");
        }
        if (user.getConfirmed()) {
            throw new UserRepositoryException("User with this email already confirmed.");
        }
        if (user.getRejected()) {
            throw new UserRepositoryException("User with this email has been rejected.");
        }

        emailService.sendEmail(user, "RegistrationConfirm",
                "submit.email.inline.html", getConfirmRegistrationLink(user));
    }
}


