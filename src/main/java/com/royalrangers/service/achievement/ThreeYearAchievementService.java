package com.royalrangers.service.achievement;

import com.dropbox.core.DbxException;
import com.royalrangers.dto.achievement.ThreeYearRequestDto;
import com.royalrangers.enums.ImageType;
import com.royalrangers.enums.UserAgeGroup;
import com.royalrangers.repository.achievement.ThreeYearAchievementRepository;
import com.royalrangers.model.achievement.ThreeYearAchievement;
import com.royalrangers.service.DropboxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ThreeYearAchievementService {

    @Autowired
    private ThreeYearAchievementRepository threeYearAchievementRepository;

    @Autowired
    private TwelveYearAchievementService twelveYearAchievementService;

    @Autowired
    private DropboxService dropboxService;

    public List<ThreeYearAchievement> getAllThreeYearAchievement() {
        return threeYearAchievementRepository.findAll();
    }

    public void addThreeYearAchievement(ThreeYearRequestDto params) {
        ThreeYearAchievement threeYearAchievementSaved = new ThreeYearAchievement();
        threeYearAchievementSaved.setName(params.getName());
        threeYearAchievementSaved.setDescription(params.getDescription());
        Integer id = params.getUpLevelId();
        threeYearAchievementSaved.setTwelveYearAchievement(twelveYearAchievementService.getTwelveYearAchievementById(id.longValue()));
        threeYearAchievementSaved.setUserAgeGroup(UserAgeGroup.valueOf(params.getAgeCategory()));
        threeYearAchievementRepository.saveAndFlush(threeYearAchievementSaved);
    }

    public ThreeYearAchievement getThreeYearAchievementById(Long id) {
        return threeYearAchievementRepository.findOne(id);
    }

    public void deleteThreeYearAchievement(Long id) {
        threeYearAchievementRepository.delete(id);
    }

    public ThreeYearAchievement editThreeYearAchievement(ThreeYearRequestDto params, Long threeYearId) {
        ThreeYearAchievement threeYearData = getThreeYearAchievementById(threeYearId);
        Integer twelveYearsId = params.getUpLevelId();
        threeYearData.setTwelveYearAchievement(twelveYearAchievementService.getTwelveYearAchievementById(twelveYearsId.longValue()));
        threeYearData.setName(params.getName());
        threeYearData.setDescription(params.getDescription());
        threeYearData.setLogoUrl(params.getLogoUrl());
        threeYearData.setUserAgeGroup(UserAgeGroup.valueOf(params.getAgeCategory()));
        return threeYearAchievementRepository.saveAndFlush(threeYearData);
    }

    public void setLogoUrl(String avatarUrl, Long threeYearId) throws DbxException {
        ThreeYearAchievement editThreeYearAchievement = threeYearAchievementRepository.findOne(threeYearId);
        if (editThreeYearAchievement.getLogoUrl() != null) {
            dropboxService.deleteImage(editThreeYearAchievement.getLogoUrl(), ImageType.THREE_YEAR_ACHIEVEMENT_LOGO);
        }
        editThreeYearAchievement.setLogoUrl(avatarUrl);
        threeYearAchievementRepository.saveAndFlush(editThreeYearAchievement);
    }

    public void deleteLogo(Long threeYearId) throws DbxException {
        ThreeYearAchievement threeYearAchievement = threeYearAchievementRepository.findOne(threeYearId);
        if (threeYearAchievement.getLogoUrl() != null) {
            dropboxService.deleteImage(threeYearAchievement.getLogoUrl(), ImageType.THREE_YEAR_ACHIEVEMENT_LOGO);
        }
        threeYearAchievement.setLogoUrl(null);
        threeYearAchievementRepository.saveAndFlush(threeYearAchievement);
    }
}