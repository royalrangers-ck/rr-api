package com.royalrangers.service.achievement;

import com.dropbox.core.DbxException;
import com.royalrangers.dto.achievement.AchievementRequestDto;
import com.royalrangers.enums.ImageType;
import com.royalrangers.repository.achievement.TwelveYearAchievementRepository;
import com.royalrangers.model.achievement.TwelveYearAchievement;
import com.royalrangers.service.DropboxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TwelveYearAchievementService {

    @Autowired
    private TwelveYearAchievementRepository twelveYearAchievementRepository;

    @Autowired
    private DropboxService dropboxService;

    public List<TwelveYearAchievement> getAllTwelveYearAchievement() {
        return twelveYearAchievementRepository.findAll();
    }

    public void addTwelveYearAchievement(AchievementRequestDto params) {
        TwelveYearAchievement twelveYearAchievement = new TwelveYearAchievement();
        twelveYearAchievement.setName(params.getName());
        twelveYearAchievement.setDescription(params.getDescription());
        twelveYearAchievement.setLogoUrl(params.getLogoUrl());
        twelveYearAchievementRepository.saveAndFlush(twelveYearAchievement);
    }

    public TwelveYearAchievement getTwelveYearAchievementById(Long id) {
        return twelveYearAchievementRepository.findOne(id);
    }

    public void deleteTwelveYearAchievement(Long id) {
        twelveYearAchievementRepository.delete(id);
    }

    public TwelveYearAchievement editTwelveYearAchievement(AchievementRequestDto params, Long twelveYearId) {
        TwelveYearAchievement twelveYearAchievement = getTwelveYearAchievementById(twelveYearId);
        twelveYearAchievement.setName(params.getName());
        twelveYearAchievement.setDescription(params.getDescription());
        twelveYearAchievement.setLogoUrl(params.getLogoUrl());
        return twelveYearAchievementRepository.saveAndFlush(twelveYearAchievement);
    }

    public void setLogoUrl(String avatarUrl, Long twelveYearId) throws DbxException {
        TwelveYearAchievement editTwelveYearAchievement = twelveYearAchievementRepository.findOne(twelveYearId);
        if (editTwelveYearAchievement.getLogoUrl() != null) {
            dropboxService.deleteImage(editTwelveYearAchievement.getLogoUrl(), ImageType.TWELVE_YEAR_ACHIEVEMENT_LOGO);
        }
        editTwelveYearAchievement.setLogoUrl(avatarUrl);
        twelveYearAchievementRepository.saveAndFlush(editTwelveYearAchievement);
    }

    public void deleteLogo(Long twelveYearId) throws DbxException {
        TwelveYearAchievement twelveYearAchievement = twelveYearAchievementRepository.findOne(twelveYearId);
        if (twelveYearAchievement.getLogoUrl() != null) {
            dropboxService.deleteImage(twelveYearAchievement.getLogoUrl(), ImageType.TWELVE_YEAR_ACHIEVEMENT_LOGO);
        }
        twelveYearAchievement.setLogoUrl(null);
        twelveYearAchievementRepository.saveAndFlush(twelveYearAchievement);
    }

}