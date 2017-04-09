package com.royalrangers.service;

import com.dropbox.core.DbxException;
import com.royalrangers.dto.ResponseResult;
import com.royalrangers.dto.PlatoonDto;
import com.royalrangers.model.Platoon;
import com.royalrangers.repository.GroupRepository;
import com.royalrangers.repository.PlatoonRepository;
import com.royalrangers.utils.ResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PlatoonService {
    @Autowired
    private DropboxService dropboxService;
    @Autowired
    PlatoonRepository platoonRepository;
    @Autowired
    GroupRepository groupRepository;

    public ResponseResult getPlatoonData(Long id) {
        Platoon platoon = platoonRepository.findOne(id);
        return ResponseBuilder.success(platoon);
    }

    public void createPlatoon(PlatoonDto platoonDto) {
        Platoon platoon = new Platoon();
        platoon.setCreateDate(platoon.getCreateDate());
        platoon.setUpdateDate(platoon.getUpdateDate());
        platoon.setName(platoonDto.getName());
        platoon.setHistory(platoonDto.getHistory());
        platoon.setAddress(platoonDto.getAddress());
        platoon.setGroup(groupRepository.findOne(platoonDto.getGroupId()));
        platoon.setCity(groupRepository.findOne(platoonDto.getGroupId()).getCity().getName());
        platoon.setMeetTime(platoonDto.getMeetTime());
        platoonRepository.save(platoon);
    }

    public void updatePlatoon(Long id, PlatoonDto update) {
        Platoon platoon = platoonRepository.findOne(id);
        platoon.setCreateDate(update.getCreateDate());
        platoon.setUpdateDate(new Date());
        platoon.setName(update.getName());
        platoon.setHistory(update.getHistory());
        platoon.setAddress(update.getAddress());
        platoon.setGroup(groupRepository.findOne(update.getGroupId()));
        platoon.setCity(groupRepository.findOne(update.getGroupId()).getCity().getName());
        platoon.setMeetTime(update.getMeetTime());
        platoonRepository.save(platoon);
    }

    public void setPlatoonLogoUrl(Long id, String logoUrl) throws DbxException {
        Platoon platoon = platoonRepository.findOne(id);

        if (platoon.getLogoUrl() != null) {
            dropboxService.deleteAvatar(platoon.getLogoUrl());
        }

        platoon.setLogoUrl(logoUrl);
        platoonRepository.save(platoon);
    }

    public void delPlatoonLogoUrl(Long id) throws DbxException {
        Platoon platoon = platoonRepository.findOne(id);

        if (platoon.getLogoUrl() != null) {
            dropboxService.deleteAvatar(platoon.getLogoUrl());
        }

        platoon.setLogoUrl(null);
        platoonRepository.save(platoon);
    }
}
