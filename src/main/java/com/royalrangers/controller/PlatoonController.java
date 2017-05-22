package com.royalrangers.controller;

import com.dropbox.core.DbxException;
import com.fasterxml.jackson.annotation.JsonView;
import com.royalrangers.dto.structure.PlatoonDto;
import com.royalrangers.dto.ResponseResult;
import com.royalrangers.enums.ImageType;
import com.royalrangers.exception.PlatoonRepositoryException;
import com.royalrangers.model.Views;
import com.royalrangers.service.DropboxService;
import com.royalrangers.service.StructureService;
import com.royalrangers.utils.ResponseBuilder;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("/platoon")
public class PlatoonController {

    @Autowired
    private DropboxService dropboxService;

    @Autowired
    private StructureService structureService;

    @JsonView(Views.Public.class)
    @GetMapping
    @ApiOperation(value = "Get platoon info for current user")
    public ResponseResult getPlatoonDetail() {
        try {
            return ResponseBuilder.success(structureService.getPlatoonData());
        } catch (PlatoonRepositoryException e) {
            return ResponseBuilder.fail(e.getMessage());
        }
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "Add platoon")
    public ResponseResult creation(@RequestBody PlatoonDto platoonDto) {
        try {
            structureService.createPlatoon(platoonDto);
            log.info("Platoon '%s' is successfully created", platoonDto.getName());
            return ResponseBuilder.success("Platoon is successfully created");
        } catch (PlatoonRepositoryException e) {
            return ResponseBuilder.fail(e.getMessage());
        }
    }

    @PutMapping
    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "Update platoon")
    public ResponseResult updatePlatoonById(@RequestParam("id") Long id, @RequestBody PlatoonDto platoonUpdate) {

        try {
            structureService.updatePlatoon(id, platoonUpdate);
            log.info("Update platoon with id %d " + id);
            return ResponseBuilder.success("Platoon with id %d is successfully updated", String.valueOf(id));

        } catch (PlatoonRepositoryException e) {
            return ResponseBuilder.fail(e.getMessage());
        }
    }

    @PostMapping("/logo")
    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "Upload and set platoon logo")
    public ResponseResult upload(@RequestParam("id") Long id, @RequestParam("file") MultipartFile file) {

        try {
            String logoUrl = dropboxService.imageUpload(file, ImageType.PLATOON_LOGO);
            log.info("Set platoon logo public URL: " + logoUrl);

            structureService.setPlatoonLogoUrl(id, logoUrl);

            return ResponseBuilder.success("logoUrl", logoUrl);

        } catch (IOException | DbxException e) {
            return ResponseBuilder.fail(e.getMessage());
        }
    }

    @DeleteMapping("/logo")
    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "Delete platoon logo")
    public ResponseResult delete(@RequestParam("id") Long id) {
        try {
            structureService.delPlatoonLogoUrl(id);

            return ResponseBuilder.success("logo deleted.");

        } catch (DbxException e) {
            return ResponseBuilder.fail(e.getMessage());
        }
    }
}
