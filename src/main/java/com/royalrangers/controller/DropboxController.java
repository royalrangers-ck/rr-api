package com.royalrangers.controller;

import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Locale;

@RestController
@Slf4j
public class DropboxController {

    @Value("${dropbox.appName}")
    private String appName;
    @Value("${dropbox.accessToken}")
    private String accessToken;

    @PostMapping("/upload")
    public String fileUpload(@RequestParam("file") MultipartFile file) throws IOException, DbxException {

        DbxRequestConfig config = new DbxRequestConfig(appName, Locale.getDefault().toString());
        DbxClientV2 client = new DbxClientV2(config, accessToken);

        if (file.isEmpty()) {
            return "Please select a file to upload";
        }
        byte[] bytes = file.getBytes();
        InputStream in = new ByteArrayInputStream(bytes);
        FileMetadata metadata = client.files().uploadBuilder("/" + file.getOriginalFilename())
                .uploadAndFinish(in);

        return "You successfully uploaded '" + file.getOriginalFilename() + "'";
    }
}
