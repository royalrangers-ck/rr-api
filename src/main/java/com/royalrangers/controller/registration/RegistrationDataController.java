package com.royalrangers.controller.registration;

import com.royalrangers.bean.ResponseResult;
import com.royalrangers.model.*;
import com.royalrangers.repository.*;
import com.royalrangers.utils.ResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RegistrationDataController {

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
