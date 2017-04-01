package com.royalrangers.service;

import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.sharing.SharedLinkMetadata;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import java.util.UUID;

@Service
public class DropboxService {
    @Value("${dropbox.appName}")
    private String appName;
    @Value("${dropbox.accessToken}")
    private String accessToken;

    public DbxClientV2 getClient() {
    DbxRequestConfig config = DbxRequestConfig.newBuilder(appName)
            .withUserLocale(Locale.getDefault().toString())
            .build();
    DbxClientV2 client = new DbxClientV2(config, accessToken);
        return client;
    }

    public void fileUpload(MultipartFile file) throws IOException, DbxException {
        byte[] bytes = file.getBytes();
        InputStream in = new ByteArrayInputStream(bytes);
        FileMetadata metadata = getClient().files().uploadBuilder("/" + file.getOriginalFilename())
                .uploadAndFinish(in);
    }

    public String avatarUpload(MultipartFile file) throws IOException, DbxException {

        String path = "/avatar/";
        String extension = getFilenameExtension(file.getOriginalFilename());
        String filename = UUID.randomUUID().toString() + extension;

        InputStream in = file.getInputStream();
        DbxClientV2 client = getClient();

        FileMetadata metadata = client.files().uploadBuilder(path + filename).uploadAndFinish(in);
        String sharedUrl = client.sharing().createSharedLinkWithSettings(path +filename).getUrl();
        String directUrl = sharedUrl
                .substring(0, sharedUrl.lastIndexOf("?"))
                .replaceAll("www.dropbox.com", "dl.dropboxusercontent.com");

        return directUrl;
    }

    private String getFilenameExtension (String filename) {
        return filename.substring(filename.lastIndexOf("."));
    }
 }
