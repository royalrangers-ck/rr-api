package com.royalrangers.controller;

import com.royalrangers.dto.ResponseResult;
import com.royalrangers.dto.structure.RegionDto;
import com.royalrangers.dto.structure.CountryDto;
import com.royalrangers.dto.structure.CityDto;
import com.royalrangers.dto.structure.SectionDto;
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

    @PostMapping("/country")
    @ApiOperation(value = "Add country")
    public ResponseResult addCountry(@RequestBody CountryDto countryDto) {
        try {
            if (!structureService.createCountry(countryDto))
                return ResponseBuilder.fail("Country with this name already exist.");
        } catch (Exception e) {
            return ResponseBuilder.fail("Error creating new country");
        }
        return ResponseBuilder.success("Country '%s' is successfully created.", countryDto.getName());
    }

    @PostMapping("/region")
    @ApiOperation(value = "Add regions")
    public ResponseResult addRegion(@RequestBody RegionDto regionDto) {
        try {
            if (!structureService.createRegion(regionDto))
                return ResponseBuilder.fail("Region with this name already exist.");
        } catch (Exception e) {
            return ResponseBuilder.fail("Error creating new regions");
        }
        return ResponseBuilder.success("Region '%s' is successfully created.", regionDto.getName());
    }

    @PostMapping("/city")
    @ApiOperation(value = "Add city")
    public ResponseResult addCity(@RequestBody CityDto cityDto) {
        try {
            if (!structureService.createCity(cityDto))
                return ResponseBuilder.fail("City with this name already exist.");
        } catch (Exception e) {
            return ResponseBuilder.fail("Error creating new city.");
        }
        return ResponseBuilder.success("City '%s' is successfully created.", cityDto.getName());
    }

    @PostMapping("/section")
    @ApiOperation(value = "Add section")
    public ResponseResult addSection(@RequestBody SectionDto sectionDto) {
        try {
            if (!structureService.createSection(sectionDto))
                return ResponseBuilder.fail("Section with this name already exist.");
        } catch (Exception e) {
            return ResponseBuilder.fail("Error creating section");
        }
        return ResponseBuilder.success("Section '%s' is successfully created.", sectionDto.getName());
    }
}
