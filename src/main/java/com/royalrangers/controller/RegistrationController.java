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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Slf4j
@RestController
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

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ResponseEntity registration(@RequestBody String jsonUser) {
        Gson gson = new Gson();
        UserBean userBean = gson.fromJson(jsonUser, UserBean.class);

        if (userService.isEmailExist(userBean.getEmail())) {
            log.info(String.format("User with email '%s' already exists", userBean.getEmail()));
            return new ResponseEntity(ResponseBuilder.fail("User with this email already exists"), HttpStatus.CONFLICT);
        }

        User user = userService.createUserFromUserForm(userBean);
        String confirmLink = userService.getConfirmRegistrationLink(user);
        emailService.sendEmail(user, "RegistrationConfirm", "submit.email.inline.html", confirmLink);

        log.info(String.format("User '%s' is successfully created", userBean.getEmail()));
        return new ResponseEntity(ResponseBuilder.success("User is successfully created"), HttpStatus.OK);
    }

    @RequestMapping(value = "/registration/confirm", method = RequestMethod.GET)
    public ResponseEntity registrationConfirm(@RequestParam("token") String token) {

        VerificationToken verificationToken = verificationTokenService.getVerificationToken(token);
        if (verificationToken == null) {
            log.info(String.format("Verification token '%s' is invalid", token));
            return new ResponseEntity(ResponseBuilder.fail("Verification token is invalid"), HttpStatus.CONFLICT);
        }

        Calendar cal = Calendar.getInstance();
        if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
            log.info(String.format("Verification token '%s' is expired", token));
            return new ResponseEntity(ResponseBuilder.fail("Verification token is expired"), HttpStatus.CONFLICT);
        }

        User user = verificationToken.getUser();
        user.setConfirmed(true);
        userRepository.save(user);

        log.info(String.format("Verification token '%s' is confirmed", token));
        return new ResponseEntity(ResponseBuilder.success("User confirm registration successfully"), HttpStatus.OK);
    }

    @PostMapping("/registration/check/email")
    public ResponseResult checkEmail(@RequestBody Email email) {

        String mail = email.getEmail();
        log.info(String.format("Checking email '%s'", mail));

        if (userService.isEmailExist(mail)) {
            return ResponseBuilder.fail("User with such an email already exists!");
        }

        return ResponseBuilder.success();
    }

    @RequestMapping("/registration/countries")
    public ResponseResult getAllCountries() {
        return ResponseBuilder.success(countryRepository.findAll());
    }

    @RequestMapping("/registration/cities")
    public ResponseResult getAllCities() {
        return ResponseBuilder.success(cityRepository.findAll());
    }

    @RequestMapping("/registration/city")
    public ResponseResult getCitiesByCountry(Long countryId) {
        List<City> cities = cityRepository.findByCountryId(countryId);
        return ResponseBuilder.success(cities);
    }

    @RequestMapping("/registration/groups")
    public ResponseResult getAllGroups() {
        return ResponseBuilder.success(groupRepository.findAll());
    }

    @RequestMapping("/registration/group")
    public ResponseResult getGroupsByCity(Long cityId) {
        return ResponseBuilder.success(groupRepository.findByCityId(cityId));
    }

    @RequestMapping("/registration/platoons")
    public ResponseResult getAllPlatoons() {
        return ResponseBuilder.success(platoonRepository.findAll());
    }

    @RequestMapping("/registration/platoon")
    public ResponseResult getPlatoonsByGroup(Long groupId) {
        List<Platoon> platoons = platoonRepository.findByGroupId(groupId);
        return ResponseBuilder.success(platoons);
    }

    @RequestMapping("/registration/sections")
    public ResponseResult getAllSections() {
        return ResponseBuilder.success(sectionRepository.findAll());
    }

    @RequestMapping("/registration/section")
    public ResponseResult getSectionsByPlatoon(Long platoonId) {
        List<Section> sections = sectionRepository.findByPlatoonId(platoonId);
        return ResponseBuilder.success(sections);
    }

}
