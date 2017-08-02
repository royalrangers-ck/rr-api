package com.royalrangers.controller;

import com.royalrangers.dto.ResponseResult;
import com.royalrangers.dto.structure.CityDto;
import com.royalrangers.dto.structure.CountryDto;
import com.royalrangers.dto.structure.RegionDto;
import com.royalrangers.dto.structure.SectionDto;
import com.royalrangers.exception.EntryAlreadyExistsException;
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
            return ResponseBuilder.success(structureService.createCountry(countryDto));
        } catch (EntryAlreadyExistsException e) {
            return ResponseBuilder.fail(e.getMessage());
        } catch (Exception e) {
            return ResponseBuilder.fail("Error creating new country");
        }
    }

    @PostMapping("/region")
    @ApiOperation(value = "Add regions")
    public ResponseResult addRegion(@RequestBody RegionDto regionDto) {
        try {
            return ResponseBuilder.success(structureService.createRegion(regionDto));
        } catch (EntryAlreadyExistsException e) {
            return ResponseBuilder.fail(e.getMessage());
        } catch (Exception e) {
            return ResponseBuilder.fail("Error creating new region");
        }
    }

    @PostMapping("/city")
    @ApiOperation(value = "Add city")
    public ResponseResult addCity(@RequestBody CityDto cityDto) {
        try {
            return ResponseBuilder.success(structureService.createCity(cityDto));
        } catch (EntryAlreadyExistsException e) {
            return ResponseBuilder.fail(e.getMessage());
        } catch (Exception e) {
            return ResponseBuilder.fail("Error creating new city.");
        }
    }

    @PostMapping("/section")
    @ApiOperation(value = "Add section")
    public ResponseResult addSection(@RequestBody SectionDto sectionDto) {
        try {
            return ResponseBuilder.success(structureService.createSection(sectionDto));
        } catch (EntryAlreadyExistsException e) {
            return ResponseBuilder.fail(e.getMessage());
        } catch (Exception e) {
            return ResponseBuilder.fail("Error creating new section.");
        }
    }
}
