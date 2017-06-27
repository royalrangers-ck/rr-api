package com.royalrangers.service.achievement;

import com.dropbox.core.DbxException;
import com.royalrangers.dto.achievement.TestRequestDto;
import com.royalrangers.enums.ImageType;
import com.royalrangers.enums.UserAgeGroup;
import com.royalrangers.enums.achivement.TestType;
import com.royalrangers.model.achievement.Test;
import com.royalrangers.repository.achievement.TestRepository;
import com.royalrangers.service.DropboxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class TestService {

    @Autowired
    private TestRepository testRepository;

    @Autowired
    private DropboxService dropboxService;

    public List<Test> getAllTest() {
        return testRepository.findAll();
    }

    public List<Test> getAllTestByUserAgeGroup(UserAgeGroup userAgeGroup) {
        return testRepository.findByUserAgeGroupsContains(new ArrayList<>(Arrays.asList(userAgeGroup)));
    }

    public Test addTest(TestRequestDto params) {
        Test test = new Test();
        test.setName(params.getName());
        test.setShortDescription(params.getShortDescription());
        test.setDescription(params.getDescription());
        test.setLogoUrl(params.getLogoUrl());
        test.setTestType(TestType.valueOf(params.getTestType()));
        testRepository.saveAndFlush(test);
        return test;
    }

    public Test getTestById(Long testId) {
        return testRepository.findOne(testId);
    }

    public void deleteTestById(Long testId) {
        testRepository.delete(testId);
    }

    public Test editTest(TestRequestDto params, Long testId) {
        Test test = getTestById(testId);
        test.setName(params.getName());
        test.setShortDescription(params.getShortDescription());
        test.setDescription(params.getDescription());
        test.setLogoUrl(params.getLogoUrl());
        test.setTestType(TestType.valueOf(params.getTestType()));
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