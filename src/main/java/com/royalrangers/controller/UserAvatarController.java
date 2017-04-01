package com.royalrangers.controller;

import com.dropbox.core.DbxException;
import com.royalrangers.bean.ResponseResult;
import com.royalrangers.service.DropboxService;
import com.royalrangers.utils.ResponseBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("/user/avatar")
public class UserAvatarController {

    @Autowired
    private DropboxService dropboxService;

    @PostMapping
    public ResponseResult upload(@RequestParam("file") MultipartFile file) {

        try {
            String avatarUrl = dropboxService.avatarUpload(file);
            log.info("Avatar URL: " +avatarUrl);

            return ResponseBuilder.success(avatarUrl);

        } catch (IOException | DbxException e) {
            return  ResponseBuilder.fail(e.getMessage());
        }
    }
}
