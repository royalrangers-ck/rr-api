package com.royalrangers.controller;

import com.dropbox.core.DbxException;
import com.royalrangers.dto.PlatoonDto;
import com.royalrangers.dto.ResponseResult;
import com.royalrangers.exception.PlatoonRepositoryException;
import com.royalrangers.service.DropboxService;
import com.royalrangers.service.PlatoonService;
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
    private PlatoonService platoonService;

    @GetMapping
    @ApiOperation(value = "Get platoon info by authenticated user")
    public ResponseResult getPlatoonDetail() {
        try {
            return platoonService.getPlatoonData();
        } catch (PlatoonRepositoryException e) {
            return ResponseBuilder.fail(e.getMessage());
        }
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "Create platoon")
    public ResponseResult creation(@RequestBody PlatoonDto platoonDto) {
        try {
            platoonService.createPlatoon(platoonDto);

            log.info(String.format("Platoon '%s' is successfully created", platoonDto.getName()));
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
            platoonService.updatePlatoon(id, platoonUpdate);
            log.info("Update platoon with id %d " + id);

            return ResponseBuilder.success(String.format("Platoon with id %d successful updated", id));

        } catch (PlatoonRepositoryException e) {
            return ResponseBuilder.fail(e.getMessage());
        }
    }

    @PostMapping("/logo")
    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "Upload and set platoon logo")
    public ResponseResult upload(@RequestParam("id") Long id, @RequestParam("file") MultipartFile file) {

        try {
            String logoUrl = dropboxService.logoUpload(file);
            log.info("Set platoon logo public URL: " + logoUrl);

            platoonService.setPlatoonLogoUrl(id, logoUrl);

            return ResponseBuilder.success("logoUrl", logoUrl);

        } catch (IOException | DbxException e) {
            return ResponseBuilder.fail(e.getMessage());
        }
    }

    @DeleteMapping("/logo")
    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "Delete logo")
    public ResponseResult delete(@RequestParam("id") Long id) {
        try {
            platoonService.delPlatoonLogoUrl(id);

            return ResponseBuilder.success("logo deleted.");

        } catch (DbxException e) {
            return ResponseBuilder.fail(e.getMessage());
        }
    }
}
