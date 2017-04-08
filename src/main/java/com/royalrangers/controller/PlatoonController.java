package com.royalrangers.controller;

import com.dropbox.core.DbxException;
import com.royalrangers.bean.PlatoonBean;
import com.royalrangers.bean.ResponseResult;
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

    @GetMapping("/get")
    @PreAuthorize("isAuthenticated()")
    @ApiOperation(value = "Get platoon info")
    public ResponseResult getPlatoonDetail(@RequestParam Long id) {
        try {
            return platoonService.getPlatoonData(id);
        } catch (PlatoonRepositoryException e) {
            return ResponseBuilder.fail(e.getMessage());
        }
    }

    @PostMapping("/create")
    @PreAuthorize("isAuthenticated()")
    @ApiOperation(value = "Create platoon")
    public ResponseResult creation(@RequestBody PlatoonBean platoonBean) {
        try {
            platoonService.createPlatoon(platoonBean);

            log.info(String.format("Platoon '%s' is successfully created", platoonBean.getName()));
            return ResponseBuilder.success("Platoon is successfully created");
        } catch (PlatoonRepositoryException e) {
            return ResponseBuilder.fail(e.getMessage());
        }
    }

    @PutMapping("/update")
    @PreAuthorize("isAuthenticated()")
    @ApiOperation(value = "Update platoon")
    public ResponseResult updatePlatoonById(@RequestParam("id") Long id, @RequestBody PlatoonBean platoonUpdate) {

        try {
            platoonService.updatePlatoon(id, platoonUpdate);
            log.info("Update platoon with id %d " + id);

            return ResponseBuilder.success(String.format("Platoon with id %d successful updated", id));

        } catch (PlatoonRepositoryException e) {
            return ResponseBuilder.fail(e.getMessage());
        }
    }

    @PostMapping("/avatar")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseResult upload(@RequestParam("id") Long id, @RequestParam("file") MultipartFile file) {

        try {
            String logoUrl = dropboxService.avatarUpload(file);
            log.info("Set platoon logo public URL: " + logoUrl);

            platoonService.setPlatoonLogoUrl(id, logoUrl);

            return ResponseBuilder.success("logoUrl", logoUrl);

        } catch (IOException | DbxException e) {
            return ResponseBuilder.fail(e.getMessage());
        }
    }

    @DeleteMapping("/avatar")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseResult delete(@RequestParam("id") Long id) {
        try {
            platoonService.delPlatoonLogoUrl(id);

            return ResponseBuilder.success("logo deleted.");

        } catch (DbxException e) {
            return ResponseBuilder.fail(e.getMessage());
        }
    }
}
