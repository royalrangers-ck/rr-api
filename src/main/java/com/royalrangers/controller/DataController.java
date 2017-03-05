package com.royalrangers.controller;

import com.royalrangers.model.*;
import com.royalrangers.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DataController {
    @Autowired
    private CountryRepository countryRepository;
    @RequestMapping("/registration/country")
    public Object getAllCountries() {
        return countryRepository.findAll();
    }

    @Autowired
    private CityRepository cityRepository;
    @RequestMapping("/registration/city")
    public Object getAllCities() {
        return cityRepository.findAll();
    }

    @RequestMapping("/registration/city/byCountry")
    public List<City> getCitiesByCountry(Long country_id) {
        List<City> cities = cityRepository.findByCountryId(country_id);
        return cities;
    }

    @Autowired
    private GroupRepository groupRepository;
    @RequestMapping("/registration/group")
    public Object getAllGroups() {
        return groupRepository.findAll();
    }

    @RequestMapping("/registration/group/byCity")
    public List<Group> getGroupsByCity(Long city_id) {
        return groupRepository.findByCity(city_id);
    }

    @Autowired
    private PlatoonRepository platoonRepository;
    @RequestMapping("/registration/platoon")
     public Object getAllPlatoons() {
        return platoonRepository.findAll();
    }

    @RequestMapping("/registration/platoon/byGroup")
    public Object getPlatoonsByGroup(Long group_id) {
        List<Platoon> platoons = platoonRepository.findByGroup(group_id);
        return platoons;
    }

    @Autowired
    private SectionRepository sectionRepository;
    @RequestMapping("/registration/section")
    public Object getAllSections() {
        return sectionRepository.findAll();
    }

    @RequestMapping("/registration/section/byPlatoon")
    public List<Section> getSectionsByPlatoon(Long platoon_id) {
        List<Section> sections = sectionRepository.findByPlatoon(platoon_id);
        return sections;
    }
}
