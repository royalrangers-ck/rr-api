package com.royalrangers.controller;

import com.royalrangers.dto.ResponseResult;
import com.royalrangers.dto.structure.CityDto;
import com.royalrangers.dto.structure.CountryDto;
import com.royalrangers.dto.structure.GroupDto;
import com.royalrangers.dto.structure.SectionDto;
import com.royalrangers.model.Country;
import com.royalrangers.repository.CountryRepository;
import com.royalrangers.service.StructureService;
import com.royalrangers.utils.ResponseBuilder;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    @Autowired
    private StructureService structureService;

    @Autowired
    private CountryRepository countryRepository;

    @PostMapping("/country")
    @ApiOperation(value = "Add country")
    public ResponseResult addCountry(@RequestBody CountryDto countryDto) {
        try {
            countryRepository.save(new Country(countryDto.getName()));
        } catch (Exception e) {
            return ResponseBuilder.fail("Error creating new country");
        }
        return ResponseBuilder.success("Country %s successfully created.", countryDto.getName());
    }

    @PostMapping("/city")
    @ApiOperation(value = "Add city")
    public ResponseResult addCity(@RequestBody CityDto cityDto) {
        try {
            if (!structureService.createCity(cityDto))
                return ResponseBuilder.fail("City with this name already exist.");
        } catch (Exception e) {
            return ResponseBuilder.fail("Error creating new city");
        }
        return ResponseBuilder.success("City %s successfully created.", cityDto.getName());
    }

    @PostMapping("/group")
    @ApiOperation(value = "Add group")
    public ResponseResult addGroup(@RequestBody GroupDto groupDto) {
        try {
            if (!structureService.createGroup(groupDto))
                return ResponseBuilder.fail("Group with this name already exist.");
        } catch (Exception e) {
            return ResponseBuilder.fail("Error creating new group.");
        }
        return ResponseBuilder.success("Group %s successfully created.", groupDto.getName());
    }

    @PostMapping("/section")
    @ApiOperation(value = "Add section")
    public ResponseResult addSection(@RequestBody SectionDto sectionDto) {
        try {
            if (!structureService.createSection(sectionDto))
                return ResponseBuilder.fail("Section with this name already exist.");
        } catch (Exception e) {
            return ResponseBuilder.fail("Error creating %s section");
        }
        return ResponseBuilder.success("Section %s successfully created.", sectionDto.getName());
    }
}
