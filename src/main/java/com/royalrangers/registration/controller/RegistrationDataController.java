package com.royalrangers.registration.controller;

import com.royalrangers.model.*;
import com.royalrangers.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RegistrationDataController {

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private SectionRepository sectionRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private PlatoonRepository platoonRepository;

    @RequestMapping("/registration/countries")
    public Object getAllCountries() {
        return countryRepository.findAll();
    }

    @RequestMapping("/registration/cities")
    public Object getAllCities() {
        return cityRepository.findAll();
    }

    @RequestMapping("/registration/city")
    public List<City> getCitiesByCountry(Long country_id) {
        List<City> cities = cityRepository.findByCountryId(country_id);
        return cities;
    }

    @RequestMapping("/registration/groups")
    public Object getAllGroups() {
        return groupRepository.findAll();
    }

    @RequestMapping("/registration/group")
    public List<Group> getGroupsByCity(Long city_id) {
        return groupRepository.findByCity(city_id);
    }

    @RequestMapping("/registration/platoons")
    public Object getAllPlatoons() {
        return platoonRepository.findAll();
    }

    @RequestMapping("/registration/platoon")
    public Object getPlatoonsByGroup(Long group_id) {
        List<Platoon> platoons = platoonRepository.findByGroup(group_id);
        return platoons;
    }

    @RequestMapping("/registration/sections")
    public Object getAllSections() {
        return sectionRepository.findAll();
    }

    @RequestMapping("/registration/section")
    public List<Section> getSectionsByPlatoon(Long platoon_id) {
        List<Section> sections = sectionRepository.findByPlatoon(platoon_id);
        return sections;
    }
}
