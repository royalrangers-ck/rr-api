package com.royalrangers.controller.registration;

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
    private CityRepository cityRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private PlatoonRepository platoonRepository;

    @Autowired
    private SectionRepository sectionRepository;

    @RequestMapping("/registration/countries")
    public Object getAllCountries() {
        return countryRepository.findAll();
    }

    @RequestMapping("/registration/cities")
    public Object getAllCities() {
        return cityRepository.findAll();
    }

    @RequestMapping("/registration/city")
    public List<City> getCitiesByCountry(Long countryId) {
        List<City> cities = cityRepository.findByCountryId(countryId);
        return cities;
    }

    @RequestMapping("/registration/groups")
    public Object getAllGroups() {
        return groupRepository.findAll();
    }

    @RequestMapping("/registration/group")
    public List<Group> getGroupsByCity(Long cityId) {
        return groupRepository.findByCityId(cityId);
    }

    @RequestMapping("/registration/platoons")
    public Object getAllPlatoons() {
        return platoonRepository.findAll();
    }

    @RequestMapping("/registration/platoon")
    public Object getPlatoonsByGroup(Long groupId) {
        List<Platoon> platoons = platoonRepository.findByGroupId(groupId);
        return platoons;
    }

    @RequestMapping("/registration/sections")
    public Object getAllSections() {
        return sectionRepository.findAll();
    }

    @RequestMapping("/registration/section")
    public List<Section> getSectionsByPlatoon(Long platoonId) {
        List<Section> sections = sectionRepository.findByPlatoonId(platoonId);
        return sections;
    }
}
