package com.royalrangers.controller;

import com.google.gson.Gson;
import com.royalrangers.bean.Email;
import com.royalrangers.bean.ResponseResult;
import com.royalrangers.bean.UserBean;
import com.royalrangers.model.*;
import com.royalrangers.repository.*;
import com.royalrangers.service.EmailService;
import com.royalrangers.service.UserService;
import com.royalrangers.service.VerificationTokenService;
import com.royalrangers.utils.ResponseBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/registration")
public class RegistrationController {

    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private VerificationTokenService verificationTokenService;

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

    @PostMapping
    public ResponseResult registration(@RequestBody String jsonUser) {
        Gson gson = new Gson();
        UserBean userBean = gson.fromJson(jsonUser, UserBean.class);

        if (userService.isEmailExist(userBean.getEmail())) {
            log.info(String.format("User with email '%s' already exists", userBean.getEmail()));
            return ResponseBuilder.fail("User with this email already exists");
        }

        User user = userService.createUserFromUserForm(userBean);
        String confirmLink = userService.getConfirmRegistrationLink(user);
        emailService.sendEmail(user, "RegistrationConfirm", "submit.email.inline.html", confirmLink);

        log.info(String.format("User '%s' is successfully created", userBean.getEmail()));
        return ResponseBuilder.success("User is successfully created");
    }

    @GetMapping("/confirm")
    public ResponseResult registrationConfirm(@RequestParam("token") String token) {

        VerificationToken verificationToken = verificationTokenService.getVerificationToken(token);
        if (verificationToken == null) {
            log.info(String.format("Verification token '%s' is invalid", token));
            return ResponseBuilder.fail("Verification token is invalid");
        }

        Calendar cal = Calendar.getInstance();
        if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
            log.info(String.format("Verification token '%s' is expired", token));
            return ResponseBuilder.fail("Verification token is expired");
        }

        User user = verificationToken.getUser();
        user.setConfirmed(true);
        userRepository.save(user);

        log.info(String.format("Verification token '%s' is confirmed", token));
        return ResponseBuilder.success("User confirm registration successfully");
    }

    @PostMapping("/check/email")
    public ResponseResult checkEmail(@RequestBody Email email) {

        String mail = email.getEmail();
        log.info(String.format("Checking email '%s'", mail));

        if (userService.isEmailExist(mail)) {
            return ResponseBuilder.fail("User with such an email already exists!");
        }

        return ResponseBuilder.success();
    }

    @GetMapping("/countries")
    public ResponseResult getAllCountries() {
        return ResponseBuilder.success(countryRepository.findAll());
    }

    @GetMapping("/cities")
    public ResponseResult getAllCities() {
        return ResponseBuilder.success(cityRepository.findAll());
    }

    @GetMapping("/city")
    public ResponseResult getCitiesByCountry(@RequestParam Long countryId) {
        List<City> cities = cityRepository.findByCountryId(countryId);
        return ResponseBuilder.success(cities);
    }

    @GetMapping("/groups")
    public ResponseResult getAllGroups() {
        return ResponseBuilder.success(groupRepository.findAll());
    }

    @GetMapping("/group")
    public ResponseResult getGroupsByCity(@RequestParam Long cityId) {
        return ResponseBuilder.success(groupRepository.findByCityId(cityId));
    }

    @GetMapping("/platoons")
    public ResponseResult getAllPlatoons() {
        return ResponseBuilder.success(platoonRepository.findAll());
    }

    @GetMapping("/platoon")
    public ResponseResult getPlatoonsByGroup(@RequestParam Long groupId) {
        List<Platoon> platoons = platoonRepository.findByGroupId(groupId);
        return ResponseBuilder.success(platoons);
    }

    @GetMapping("/sections")
    public ResponseResult getAllSections() {
        return ResponseBuilder.success(sectionRepository.findAll());
    }

    @GetMapping("/section")
    public ResponseResult getSectionsByPlatoon(@RequestParam Long platoonId) {
        List<Section> sections = sectionRepository.findByPlatoonId(platoonId);
        return ResponseBuilder.success(sections);
    }
}
