package com.royalrangers.service;

import com.dropbox.core.DbxException;
import com.royalrangers.bean.ResponseResult;
import com.royalrangers.bean.PlatoonBean;
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

    public void createPlatoon(PlatoonBean platoonBean) {
        Platoon platoon = new Platoon();
        platoon.setCreateDate(platoon.getCreateDate());
        platoon.setUpdateDate(platoon.getUpdateDate());
        platoon.setName(platoonBean.getName());
        platoon.setHistory(platoonBean.getHistory());
        platoon.setAddress(platoonBean.getAddress());
        platoon.setGroup(groupRepository.findOne(platoonBean.getGroupId()));
        platoon.setCity(groupRepository.findOne(platoonBean.getGroupId()).getCity().getName());
        platoon.setMeetTime(platoonBean.getMeetTime());
        platoonRepository.save(platoon);
    }

    public void updatePlatoon(Long id, PlatoonBean update) {
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
