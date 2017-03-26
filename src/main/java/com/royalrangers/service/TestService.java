package com.royalrangers.service;

import com.royalrangers.enums.achivement.TestType;
import com.royalrangers.model.achievement.Test;
import com.royalrangers.repository.TestRepository;
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

    public void addTest(Map<String, Object> params) {
        Test test = new Test();
        Integer quarterId = (Integer) params.get("quarterAchievement");
        test.setQuarterAchievement(quarterAchievementService.getQuarterAchievementById(quarterId.longValue()));
        Integer testType = (Integer) params.get("testType");
        test.setTestType(TestType.values()[testType]);
        testRepository.saveAndFlush(test);
    }

    public Test getTestById(Long testId) {
        return testRepository.findOne(testId);
    }

    public void deleteTestById(Long testId) {
        testRepository.delete(testId);
    }

    public Test editTest(Map<String, Object> params, Long testId) {
        Test test = getTestById(testId);
        Integer quarterId = (Integer) params.get("quarterAchievement");
        test.setQuarterAchievement(quarterAchievementService.getQuarterAchievementById(quarterId.longValue()));
        Integer testType = (Integer) params.get("testType");
        test.setTestType(TestType.values()[testType]);
        return testRepository.saveAndFlush(test);
    }

}