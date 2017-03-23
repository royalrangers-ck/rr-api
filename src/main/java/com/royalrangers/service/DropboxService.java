package com.royalrangers.service;

import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;

@Service
public class DropboxService {
    @Value("${dropbox.appName}")
    private String appName;
    @Value("${dropbox.accessToken}")
    private String accessToken;

    public DbxClientV2 getClient() {
    DbxRequestConfig config = new DbxRequestConfig(appName, Locale.getDefault().toString());
    DbxClientV2 client = new DbxClientV2(config, accessToken);
        return client;
    }

    public void fileUpload(MultipartFile file) throws IOException, DbxException {
        byte[] bytes = file.getBytes();
        InputStream in = new ByteArrayInputStream(bytes);
        FileMetadata metadata = getClient().files().uploadBuilder("/" + file.getOriginalFilename())
                .uploadAndFinish(in);
    }
}
