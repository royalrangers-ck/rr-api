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
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/avatar")
public class AvatarController {

    @Autowired
    private DropboxService dropboxService;

    @PostMapping
    public ResponseResult upload(@RequestParam("file") MultipartFile file) {

        try {
            String avatarUrl = dropboxService.avatarUpload(file);
            log.info("Avatar public URL: " +avatarUrl);

            return ResponseBuilder.success("avatarUrl", avatarUrl);

        } catch (IOException | DbxException e) {
            return  ResponseBuilder.fail(e.getMessage());
        }
    }

    @DeleteMapping
    public ResponseResult delete(@RequestBody Map<String, Object> params) {
        String avatarUrl = (String) params.get("avatarUrl");
        try {
            dropboxService.deleteAvatar(avatarUrl);
            log.info("Delete avatar: " + avatarUrl);

            return ResponseBuilder.success(avatarUrl + " deleted.");

        } catch (DbxException e) {
            return ResponseBuilder.fail(e.getMessage());
        }
    }
}
