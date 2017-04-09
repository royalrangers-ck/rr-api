package com.royalrangers.service;

import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
    @Value("${dropbox.avatarPrefix}")
    private String avatarPrefix;
    @Value("${dropbox.logoPrefix}")
    private String logoPrefix;

    public DbxClientV2 getClient() {
    DbxRequestConfig config = DbxRequestConfig.newBuilder(appName)
            .withUserLocale(Locale.getDefault().toString())
            .build();
    DbxClientV2 client = new DbxClientV2(config, accessToken);
        return client;
    }

    public void fileUpload(MultipartFile file) throws IOException, DbxException {
        InputStream in = file.getInputStream();
        FileMetadata metadata = getClient().files().uploadBuilder("/" + file.getOriginalFilename())
                .uploadAndFinish(in);
    }

    public String avatarUpload(MultipartFile file) throws IOException, DbxException {

        String extension = getFilenameExtension(file.getOriginalFilename());
        String path = avatarPrefix + UUID.randomUUID().toString() + extension;

        InputStream in = file.getInputStream();
        getClient().files().uploadBuilder(path).uploadAndFinish(in);

        return getSharedLink(path);
    }

    public String logoUpload(MultipartFile file) throws IOException, DbxException {

        String extension = getFilenameExtension(file.getOriginalFilename());
        String path = logoPrefix + UUID.randomUUID().toString() + extension;

        InputStream in = file.getInputStream();
        getClient().files().uploadBuilder(path).uploadAndFinish(in);

        return getSharedPlatoonLogoLink(path);
    }

    public void deleteAvatar(String avatarUrl) throws DbxException {
        String filename = avatarUrl.substring(avatarUrl.lastIndexOf("/") + 1);
        String path = avatarPrefix + filename;
        getClient().files().delete(path);
    }
    public void deleteLogo(String logoUrl) throws DbxException {
        String filename = logoUrl.substring(logoUrl.lastIndexOf("/") + 1);
        String path = logoPrefix + filename;
        getClient().files().delete(path);
    }

    private String getFilenameExtension (String filename) {
        return filename.substring(filename.lastIndexOf("."));
    }

    private String getSharedLink(String path) throws DbxException {
        String sharedUrl = getClient().sharing().createSharedLinkWithSettings(path).getUrl();
        String directUrl = sharedUrl
                .substring(0, sharedUrl.lastIndexOf("?"))
                .replaceAll("www.dropbox.com", "dl.dropboxusercontent.com");

        return directUrl;
    }
    private String getSharedPlatoonLogoLink(String path) throws DbxException {
        String sharedUrl = getClient().sharing().createSharedLinkWithSettings(path).getUrl();
        String directUrl = sharedUrl
                .substring(0, sharedUrl.lastIndexOf("?"))
                .replaceAll("www.dropbox.com", "dl.dropboxplatooncontent.com");

        return directUrl;
    }
 }
