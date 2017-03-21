package com.royalrangers.service;

import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Getter
@Setter
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
}
