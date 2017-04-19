package com.royalrangers.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.royalrangers.dto.ResponseResult;
import com.royalrangers.enums.UserRank;
import com.royalrangers.model.City;
import com.royalrangers.model.Platoon;
import com.royalrangers.model.Section;
import com.royalrangers.model.Views;
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

    @JsonView(Views.Public.class)
    @GetMapping("/countries")
    public ResponseResult getAllCountries() {
        return ResponseBuilder.success(countryRepository.findAll());
    }

    @JsonView(Views.Public.class)
    @GetMapping("/cities")
    public ResponseResult getAllCities() {
        return ResponseBuilder.success(cityRepository.findAll());
    }

    @JsonView(Views.Public.class)
    @GetMapping("/city")
    public ResponseResult getCitiesByCountry(@RequestParam Long countryId) {
        List<City> cities = cityRepository.findByCountryId(countryId);
        return ResponseBuilder.success(cities);
    }

    @JsonView(Views.Public.class)
    @GetMapping("/groups")
    public ResponseResult getAllGroups() {
        return ResponseBuilder.success(groupRepository.findAll());
    }

    @JsonView(Views.Public.class)
    @GetMapping("/group")
    public ResponseResult getGroupsByCity(@RequestParam Long cityId) {
        return ResponseBuilder.success(groupRepository.findByCityId(cityId));
    }

    @JsonView(Views.Public.class)
    @GetMapping("/platoons")
    public ResponseResult getAllPlatoons() {
        return ResponseBuilder.success(platoonRepository.findAll());
    }

    @JsonView(Views.Public.class)
    @GetMapping("/platoon")
    public ResponseResult getPlatoonsByGroup(@RequestParam Long groupId) {
        List<Platoon> platoons = platoonRepository.findByGroupId(groupId);
        return ResponseBuilder.success(platoons);
    }

    @JsonView(Views.Public.class)
    @GetMapping("/sections")
    public ResponseResult getAllSections() {
        return ResponseBuilder.success(sectionRepository.findAll());
    }

    @JsonView(Views.Public.class)
    @GetMapping("/section")
    public ResponseResult getSectionsByPlatoon(@RequestParam Long platoonId) {
        List<Section> sections = sectionRepository.findByPlatoonId(platoonId);
        return ResponseBuilder.success(sections);
    }

}
