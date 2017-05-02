package com.royalrangers.controller;

import com.royalrangers.dto.ResponseResult;
import com.royalrangers.model.*;
import com.royalrangers.repository.CityRepository;
import com.royalrangers.repository.CountryRepository;
import com.royalrangers.repository.PlatoonRepository;
import com.royalrangers.repository.SectionRepository;
import com.royalrangers.utils.ResponseBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@Slf4j
@RestController
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private PlatoonRepository platoonRepository;

    @Autowired
    private SectionRepository sectionRepository;

    @PostMapping("/country")
    public ResponseResult createCountry(@RequestBody String countryName){
        try {
            countryRepository.save(new Country(countryName));
        } catch (Exception e){
            return ResponseBuilder.fail("Error creating new country");
        }
        return ResponseBuilder.success("Country %s successfully created.", countryName);
    }

    @PostMapping("/city")
    public ResponseResult createCity(@RequestBody Long countryId, @RequestBody String cityName){
        try {
            Country country = countryRepository.findOne(countryId);
            City city = new City(country, cityName);
            Set<City> citySet = country.getCity();
            if (citySet.add(city))
                country.setCity(citySet);
            else return ResponseBuilder.fail("City with this name already exist.");
            countryRepository.save(country);
        } catch (Exception e){
            return ResponseBuilder.fail("Error creating new city");
        }
        return ResponseBuilder.success("City %s successfully created.", cityName);
    }

    @PostMapping("/group")
    public ResponseResult createGroup(@RequestBody Long cityId, @RequestBody String groupName){
        try {
            City city = cityRepository.findOne(cityId);
            Group group = new Group(city,groupName);
            Set<Group> groupsSet = city.getGroups();
            if (groupsSet.add(group))
                city.setGroups(groupsSet);
            else return ResponseBuilder.fail("Group with this name already exist.");
            cityRepository.save(city);
        } catch (Exception e){
            return ResponseBuilder.fail("Error creating new group.");
        }
        return ResponseBuilder.success("Group %s successfully created.", groupName);
    }

    @PostMapping("/section")
    public ResponseResult createSection(@RequestBody Long platoonId, String sectionName){
        try{
            Platoon platoon = platoonRepository.findOne(platoonId);
            sectionRepository.save(new Section(platoon, sectionName));
        } catch (Exception e){
            return ResponseBuilder.fail("Error creating %s section");
        }
        return ResponseBuilder.success("Section %s successfully created.", sectionName);
    }
}
