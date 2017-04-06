package com.royalrangers.service.achievement;

import com.royalrangers.dto.achievement.TestRequestDTO;
import com.royalrangers.enums.achivement.TestType;
import com.royalrangers.model.achievement.Test;
import com.royalrangers.repository.achievement.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TestService {

    @Autowired
    private TestRepository testRepository;

    @Autowired
    private QuarterAchievementService quarterAchievementService;

    public List<Test> getAllTest() {
        return testRepository.findAll();
    }

    public void addTest(TestRequestDTO params) {
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

    public Test editTest(TestRequestDTO params, Long testId) {
        Test test = getTestById(testId);
        Integer quarterId = params.getQuarterAchievementId();
        test.setQuarterAchievement(quarterAchievementService.getQuarterAchievementById(quarterId.longValue()));
        Integer testType = params.getTestType();
        test.setTestType(TestType.values()[testType]);
        return testRepository.saveAndFlush(test);
    }

}