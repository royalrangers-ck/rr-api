package com.royalrangers.service;

import com.dropbox.core.DbxException;
import com.royalrangers.dto.achievement.UserAchievementBean;
import com.royalrangers.dto.user.UserDTO;
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

    public User createUserFromUserForm(UserDTO userDTO) {
        User user = new User();
        user.setCreateDate(user.getCreateDate());
        user.setUpdateDate(user.getUpdateDate());
        user.setEmail(userDTO.getEmail());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setEmail(userDTO.getEmail());
        user.setEnabled(false);
        user.setConfirmed(false);
        user.setApproved(false);
        user.setUserAgeGroup(determineUserAgeGroup(calculateUserAge(userDTO.getBirthDate())));
        user.setLastPasswordResetDate(new Date(System.currentTimeMillis()));
        user.setGender(userDTO.getGender());
        user.setTelephoneNumber(userDTO.getTelephoneNumber());
        user.setBirthDate(userDTO.getBirthDate());
        user.setCountry(countryRepository.findOne(userDTO.getCountryId()));
        user.setCity(cityRepository.findOne(userDTO.getCityId()));
        user.setGroup(groupRepository.findOne(userDTO.getGroupId()));
        user.setPlatoon(platoonRepository.findOne(userDTO.getPlatoonId()));
        user.setSection(sectionRepository.findOne(userDTO.getSectionId()));
        if (Objects.equals(userDTO.getStatus(), Status.TEACHER)) {
            grantAuthority(user, AuthorityName.ROLE_USER, AuthorityName.ROLE_ADMIN);
        } else {
            grantAuthority(user, AuthorityName.ROLE_USER);
        }
        return user;
    }

    public static UserDTO buildUserBean(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setCreateDate(user.getCreateDate());
        userDTO.setUpdateDate(user.getUpdateDate());
        userDTO.setId(user.getId());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setGender(user.getGender());
        userDTO.setEnabled(user.getEnabled());
        userDTO.setConfirmed(user.getConfirmed());
        userDTO.setApproved(user.getApproved());
        userDTO.setBirthDate(user.getBirthDate());
        userDTO.setTelephoneNumber(user.getTelephoneNumber());
        userDTO.setUserAgeGroup(user.getUserAgeGroup());
        userDTO.setCountryId(user.getCountry().getId());
        userDTO.setCityId(user.getCity().getId());
        userDTO.setGroupId(user.getGroup().getId());
        userDTO.setPlatoonId(user.getPlatoon().getId());
        userDTO.setSectionId(user.getSection().getId());
        return userDTO;
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

    public List<UserDTO> getUsersForApprove(Long platoonId) {
        List<User> listUsersToApprove = getUsersToApproveByPlatoonID(platoonId);
        List<UserDTO> listUsersBeanToApprove = new ArrayList<>();
        for (User user : listUsersToApprove) {
            UserDTO userDTO = buildUserBean(user);
            listUsersBeanToApprove.add(userDTO);
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

    public void updateUserByEmail(String email, UserDTO update) {

        User user = userRepository.findByEmail(email);

        if(!isAuthenticatedUserHasRole("ROLE_ADMIN")) {
            updateUserItself(user, update);
        } else {
            updateUserByAdmin(user, update);
        }
    }

    public void updateUserById(Long id, UserDTO update) {
        if(!userRepository.exists(id)) {
            throw new UserRepositoryException("Not found user with id " + id);
        }
        User user = userRepository.findOne(id);
        updateUserByAdmin(user, update);
    }

    public void updateUserByAdmin(User user, UserDTO update) {
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

    private void updateUserItself(User user, UserDTO update) {
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

