package com.royalrangers.controller;

import com.dropbox.core.DbxException;
import com.dropbox.core.v2.files.FileMetadata;
import com.royalrangers.service.DropboxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@RestController
public class DropboxController {
    @Autowired
    DropboxService dropboxService;

    @PostMapping("/upload")
    public String fileUpload(@RequestParam("file") MultipartFile file) throws IOException, DbxException {

        if (file.isEmpty()) {
            return "Please select a file to upload";
        }
        byte[] bytes = file.getBytes();
        InputStream in = new ByteArrayInputStream(bytes);
        FileMetadata metadata = dropboxService.getClient().files().uploadBuilder("/" + file.getOriginalFilename())
                .uploadAndFinish(in);

        return "You successfully uploaded '" + file.getOriginalFilename() + "'";
    }
}
