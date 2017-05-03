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
import io.swagger.annotations.ApiOperation;
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
    @ApiOperation(value = "Get a list of ranks")
    public ResponseResult getUserRankList() {
        List<Enum> rankList = Arrays.asList(UserRank.values());
        return ResponseBuilder.success(rankList);
    }

    @JsonView(Views.Public.class)
    @GetMapping("/countries")
    @ApiOperation(value = "Get a list of countries")
    public ResponseResult getAllCountries() {
        return ResponseBuilder.success(countryRepository.findAll());
    }

    @JsonView(Views.Public.class)
    @GetMapping("/cities")
    @ApiOperation(value = "Get a list of cities")
    public ResponseResult getAllCities() {
        return ResponseBuilder.success(cityRepository.findAll());
    }

    @JsonView(Views.Public.class)
    @GetMapping("/city")
    @ApiOperation(value = "Get a list of cites for given country")
    public ResponseResult getCitiesByCountry(@RequestParam Long countryId) {
        List<City> cities = cityRepository.findByCountryId(countryId);
        return ResponseBuilder.success(cities);
    }

    @JsonView(Views.Public.class)
    @GetMapping("/groups")
    @ApiOperation(value = "Get a list of groups")
    public ResponseResult getAllGroups() {
        return ResponseBuilder.success(groupRepository.findAll());
    }

    @JsonView(Views.Public.class)
    @GetMapping("/group")
    @ApiOperation(value = "Get a list of groups for given city")
    public ResponseResult getGroupsByCity(@RequestParam Long cityId) {
        return ResponseBuilder.success(groupRepository.findByCityId(cityId));
    }

    @JsonView(Views.Public.class)
    @GetMapping("/platoons")
    @ApiOperation(value = "Get a list of platoons")
    public ResponseResult getAllPlatoons() {
        return ResponseBuilder.success(platoonRepository.findAll());
    }

    @JsonView(Views.Public.class)
    @GetMapping("/platoon")
    @ApiOperation(value = "Get a list of platoons for given group")
    public ResponseResult getPlatoonsByGroup(@RequestParam Long groupId) {
        List<Platoon> platoons = platoonRepository.findByGroupId(groupId);
        return ResponseBuilder.success(platoons);
    }

    @JsonView(Views.Public.class)
    @GetMapping("/")
    @ApiOperation(value = "Get a list of sections")
    public ResponseResult getAllSections() {
        return ResponseBuilder.success(sectionRepository.findAll());
    }

    @JsonView(Views.Public.class)
    @GetMapping("/section")
    @ApiOperation(value = "Get a list of sections for given platoon")
    public ResponseResult getSectionsByPlatoon(@RequestParam Long platoonId) {
        List<Section> sections = sectionRepository.findByPlatoonId(platoonId);
        return ResponseBuilder.success(sections);
    }
}
