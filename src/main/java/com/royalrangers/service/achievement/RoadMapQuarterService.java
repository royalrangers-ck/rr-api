package com.royalrangers.service.achievement;

import com.royalrangers.dto.achievement.RoadMapQuarterRequestDto;
import com.royalrangers.model.achievement.*;
import com.royalrangers.repository.achievement.RoadMapQuarterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoadMapQuarterService {

    @Autowired
    private RoadMapQuarterRepository roadMapQuarterRepository;

    @Autowired
    private QuarterAchievementService quarterAchievementService;

    @Autowired
    private TestService testService;

    public List<RoadMapQuarterAchievement> getAllMapForSpecialSection(Long sectionId){
        return roadMapQuarterRepository.findBySectionId(sectionId);
    }

    public RoadMapQuarterAchievement saveRoadMapQuarter(RoadMapQuarterRequestDto roadMapQuarterRequestDto){
        RoadMapQuarterAchievement roadMapQuarter = roadMapQuarterRepository.findBySectionIdAndQuarterAchievementId(roadMapQuarterRequestDto.getSectionId(), roadMapQuarterRequestDto.getQuarterId());
        QuarterAchievement quarterAchievement = quarterAchievementService.getQuarterAchievementById(roadMapQuarterRequestDto.getQuarterId());
        ArrayList<Test> tests = new ArrayList<>();
        roadMapQuarterRequestDto.getTestIds().forEach(id -> {
            tests.add(testService.getTestById(id));
        });
        if (roadMapQuarter == null) {
            RoadMapQuarterAchievement roadMapQuarterAchievement = new RoadMapQuarterAchievement();
            roadMapQuarterAchievement.setSectionId(roadMapQuarterRequestDto.getSectionId());
            roadMapQuarterAchievement.setQuarterAchievement(quarterAchievement);
            roadMapQuarterAchievement.setTests(tests);
            roadMapQuarterRepository.save(roadMapQuarterAchievement);
        } else {
            editRoadMapYear(roadMapQuarterRequestDto);
        }
        return roadMapQuarterRepository.findBySectionIdAndQuarterAchievementId(roadMapQuarterRequestDto.getSectionId(), roadMapQuarterRequestDto.getQuarterId());
    }

    private RoadMapQuarterAchievement editRoadMapYear(RoadMapQuarterRequestDto roadMapQuarterRequestDto) {
        RoadMapQuarterAchievement roadMapQuarter = roadMapQuarterRepository.findBySectionIdAndQuarterAchievementId(roadMapQuarterRequestDto.getSectionId(), roadMapQuarterRequestDto.getQuarterId());
        ArrayList<Test> tests = new ArrayList<>();
        roadMapQuarterRequestDto.getTestIds().forEach(id -> {
            tests.add(testService.getTestById(id));
        });
        roadMapQuarter.setTests(tests);
        return roadMapQuarterRepository.saveAndFlush(roadMapQuarter);
    }

}
