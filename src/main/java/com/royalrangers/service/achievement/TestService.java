package com.royalrangers.service.achievement;

import com.dropbox.core.DbxException;
import com.royalrangers.dto.achievement.TestRequestDto;
import com.royalrangers.enums.ImageType;
import com.royalrangers.enums.achivement.TestType;
import com.royalrangers.model.achievement.Test;
import com.royalrangers.repository.achievement.TestRepository;
import com.royalrangers.service.DropboxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestService {

    @Autowired
    private TestRepository testRepository;

    @Autowired
    private QuarterAchievementService quarterAchievementService;

    @Autowired
    private DropboxService dropboxService;

    public List<Test> getAllTest() {
        return testRepository.findAll();
    }

    public void addTest(TestRequestDto params) {
        Test test = new Test();
        Integer quarterId = params.getQuarterAchievementId();
        test.setQuarterAchievement(quarterAchievementService.getQuarterAchievementById(quarterId.longValue()));
        Integer testType = params.getTestType();
        test.setTestType(TestType.values()[testType]);
        testRepository.saveAndFlush(test);
    }

    public Test getTestById(Long testId) {
        return testRepository.findOne(testId);
    }

    public void deleteTestById(Long testId) {
        testRepository.delete(testId);
    }

    public Test editTest(TestRequestDto params, Long testId) {
        Test test = getTestById(testId);
        Integer quarterId = params.getQuarterAchievementId();
        test.setQuarterAchievement(quarterAchievementService.getQuarterAchievementById(quarterId.longValue()));
        Integer testType = params.getTestType();
        test.setTestType(TestType.values()[testType]);
        return testRepository.saveAndFlush(test);
    }

    public void setLogoUrl(String avatarUrl, Long testId) throws DbxException {
        Test editTestLogo = testRepository.findOne(testId);
        if (editTestLogo.getLogoUrl() != null) {
            dropboxService.deleteImage(editTestLogo.getLogoUrl(), ImageType.TEST_LOGO);
        }

        editTestLogo.setLogoUrl(avatarUrl);
        testRepository.saveAndFlush(editTestLogo);
    }

    public void deleteTestLogo(Long testId) throws DbxException {
        Test test = testRepository.findOne(testId);

        if (test.getLogoUrl() != null) {
            dropboxService.deleteImage(test.getLogoUrl(), ImageType.TEST_LOGO);
        }
        test.setLogoUrl(null);
        testRepository.saveAndFlush(test);
    }

}