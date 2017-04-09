package com.royalrangers.controller;

import com.royalrangers.dto.ResponseResult;
import com.royalrangers.enums.UserRank;
import com.royalrangers.model.City;
import com.royalrangers.model.Platoon;
import com.royalrangers.model.Section;
import com.royalrangers.repository.*;
import com.royalrangers.utils.ResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/public")
public class PublicInformationController {

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

    @GetMapping("/ranks")
    public @ResponseBody
    ResponseResult getUserRankList() {
        List<Enum> rankList = Arrays.asList(UserRank.values());
        return ResponseBuilder.success(rankList);
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
