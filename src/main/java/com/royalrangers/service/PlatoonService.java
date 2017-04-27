package com.royalrangers.service;

import com.dropbox.core.DbxException;
import com.royalrangers.dto.PlatoonDto;
import com.royalrangers.enums.ImageType;
import com.royalrangers.model.Platoon;
import com.royalrangers.repository.GroupRepository;
import com.royalrangers.repository.PlatoonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PlatoonService {

    @Autowired
    private DropboxService dropboxService;

    @Autowired
    private PlatoonRepository platoonRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private UserService userService;

    public Platoon getPlatoonData() {
        return platoonRepository.findOne(userService.getAuthenticatedUser().getPlatoon().getId());
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
            dropboxService.deleteImage(platoon.getLogoUrl(), ImageType.PLATOON_LOGO);
        }

        platoon.setLogoUrl(logoUrl);
        platoonRepository.save(platoon);
    }

    public void delPlatoonLogoUrl(Long id) throws DbxException {
        Platoon platoon = platoonRepository.findOne(id);

        if (platoon.getLogoUrl() != null) {
            dropboxService.deleteImage(platoon.getLogoUrl(), ImageType.PLATOON_LOGO);
        }

        platoon.setLogoUrl(null);
        platoonRepository.save(platoon);
    }
}
