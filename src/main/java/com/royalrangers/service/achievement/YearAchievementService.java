package com.royalrangers.service.achievement;

import com.dropbox.core.DbxException;
import com.royalrangers.dto.achievement.AchievementRequestDto;
import com.royalrangers.enums.ImageType;
import com.royalrangers.repository.achievement.YearAchievementRepository;
import com.royalrangers.model.achievement.YearAchievement;
import com.royalrangers.service.DropboxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class YearAchievementService {

    @Autowired
    private YearAchievementRepository yearAchievementRepository;

    @Autowired
    private ThreeYearAchievementService threeYearAchievementService;

    @Autowired
    private DropboxService dropboxService;

    public List<YearAchievement> getAllYearAchievement() {
        return yearAchievementRepository.findAll();
    }

    public void addYearAchievement(AchievementRequestDto params) {
        YearAchievement yearAchievementSaved = new YearAchievement();
        yearAchievementSaved.setName(params.getName());
        yearAchievementSaved.setDescription(params.getDescription());
        Integer id = params.getUpLevelId();
        yearAchievementSaved.setThreeYearAchievement(threeYearAchievementService.getThreeYearAchievementById(id.longValue()));
        yearAchievementRepository.saveAndFlush(yearAchievementSaved);
    }

    public YearAchievement getYearAchievementById(Long id) {
        return yearAchievementRepository.findOne(id);
    }

    public void deleteYearAchievement(Long id) {
        yearAchievementRepository.delete(id);
    }

    public YearAchievement editYearAchievement(AchievementRequestDto params, Long yearId) {
        YearAchievement editYearData = getYearAchievementById(yearId);
        Integer editThreeYearId = params.getUpLevelId();
        editYearData.setName(params.getName());
        editYearData.setDescription(params.getDescription());
        editYearData.setLogoUrl(params.getLogoUrl());
        editYearData.setThreeYearAchievement(threeYearAchievementService.getThreeYearAchievementById(editThreeYearId.longValue()));
        return yearAchievementRepository.saveAndFlush(editYearData);
    }

    public void setLogoUrl(String avatarUrl, Long testId) throws DbxException {
        YearAchievement editYearAchievement = yearAchievementRepository.findOne(testId);
        if (editYearAchievement.getLogoUrl() != null) {
            dropboxService.deleteImage(editYearAchievement.getLogoUrl(), ImageType.YEAR_ACHIEVEMENT_LOGO);
        }
        editYearAchievement.setLogoUrl(avatarUrl);
        yearAchievementRepository.saveAndFlush(editYearAchievement);
    }

    public void deleteLogo(Long testId) throws DbxException {
        YearAchievement yearAchievement = yearAchievementRepository.findOne(testId);
        if (yearAchievement.getLogoUrl() != null) {
            dropboxService.deleteImage(yearAchievement.getLogoUrl(), ImageType.YEAR_ACHIEVEMENT_LOGO);
        }
        yearAchievement.setLogoUrl(null);
        yearAchievementRepository.saveAndFlush(yearAchievement);
    }

}