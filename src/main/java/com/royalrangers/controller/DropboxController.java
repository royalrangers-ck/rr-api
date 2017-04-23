package com.royalrangers.controller;

import com.dropbox.core.DbxException;
import com.royalrangers.dto.ResponseResult;
import com.royalrangers.service.DropboxService;
import com.royalrangers.utils.ResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@RestController
public class DropboxController {

    @Autowired
    private DropboxService dropboxService;

    @PostMapping("/upload")
    public ResponseResult upload(@RequestParam("file") MultipartFile file) throws IOException, DbxException {
        if (file.isEmpty()) {
            return ResponseBuilder.success("Please select a file to upload");
        }
        dropboxService.fileUpload(file);
        return ResponseBuilder.success("You successfully uploaded '" + file.getOriginalFilename() + "'");
    }
}
