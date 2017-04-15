package com.royalrangers.service.achievement;

import com.royalrangers.dto.achievement.PlatoonAchievementDto;
import com.royalrangers.dto.achievement.PlatoonRewardDto;
import com.royalrangers.enums.achivement.AchievementState;
import com.royalrangers.enums.achivement.AchievementType;
import com.royalrangers.enums.achivement.RewardType;
import com.royalrangers.model.User;
import com.royalrangers.model.achievement.*;
import com.royalrangers.repository.UserRepository;
import com.royalrangers.repository.achievement.*;
import com.royalrangers.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlatoonAchievementService {
    @Autowired
    private UserTwelveYearAchievementRepository userTwelveYearAchievementRepository;
    @Autowired
    private UserThreeYearAchievementRepository userThreeYearAchievementRepository;
    @Autowired
    private UserYearAchievementRepository userYearAchievementRepository;
    @Autowired
    private UserQuarterAchievementRepository userQuarterAchievementRepository;
    @Autowired
    private UserRewardRepository userRewardRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

    public List<PlatoonAchievementDto> findAllTwelveYearAchievements() {
        String email = userService.getAuthenticatedUserEmail();
        User user = userRepository.findByEmail(email);
        List<UserTwelveYearAchievement> list = userTwelveYearAchievementRepository.findAllByUser_PlatoonId(user.getPlatoon().getId());
        List<PlatoonAchievementDto> result = new ArrayList<>();
        for (UserTwelveYearAchievement item : list) {
            if (item.getAchievementState().equals(AchievementState.APPROVED)) {
                result.add(buildUserAchievement(item));
            }
        }
        return result;
    }

    public List<PlatoonAchievementDto> findAllThreeYearAchievements() {
        String email = userService.getAuthenticatedUserEmail();
        User user = userRepository.findByEmail(email);
        List<UserThreeYearAchievement> list = userThreeYearAchievementRepository.findByUser_PlatoonId(user.getPlatoon().getId());
        List<PlatoonAchievementDto> result = new ArrayList<>();
        for (UserThreeYearAchievement item : list) {
            if (item.getAchievementState().equals(AchievementState.APPROVED)) {
                result.add(buildUserAchievement(item));
            }
        }
        return result;
    }

    public List<PlatoonAchievementDto> findAllYearAchievements() {
        String email = userService.getAuthenticatedUserEmail();
        User user = userRepository.findByEmail(email);
        List<UserYearAchievement> list = userYearAchievementRepository.findByUser_PlatoonId(user.getPlatoon().getId());
        List<PlatoonAchievementDto> result = new ArrayList<>();
        for (UserYearAchievement item : list) {
            if (item.getAchievementState().equals(AchievementState.APPROVED)) {
                result.add(buildUserAchievement(item));
            }
        }
        return result;
    }

    public List<PlatoonAchievementDto> findAllQuarterYearAchievements() {
        String email = userService.getAuthenticatedUserEmail();
        User user = userRepository.findByEmail(email);
        List<UserQuarterAchievement> list = userQuarterAchievementRepository.findByUser_PlatoonId(user.getPlatoon().getId());
        List<PlatoonAchievementDto> result = new ArrayList<>();
        for (UserQuarterAchievement item : list) {
            if (item.getAchievementState().equals(AchievementState.APPROVED)) {
                result.add(buildUserAchievement(item));
            }
        }
        return result;
    }

    public List<PlatoonRewardDto> findAllMedalRewards() {
        String email = userService.getAuthenticatedUserEmail();
        User user = userRepository.findByEmail(email);
        List<PlatoonRewardDto> rewards = new ArrayList<>();
        List<UserReward> list = userRewardRepository.findByUser_PlatoonId(user.getPlatoon().getId());
        for (UserReward item : list) {
            if (item.getAchievementState().equals(AchievementState.APPROVED) && item.getReward().getRewardType().equals(RewardType.MEDAL)) {
                rewards.add(buildUserReward(item));
            }
        }
        return rewards;
    }

    public List<PlatoonRewardDto> findAllLathRewards() {
        String email = userService.getAuthenticatedUserEmail();
        User user = userRepository.findByEmail(email);
        List<PlatoonRewardDto> rewards = new ArrayList<>();
        List<UserReward> list = userRewardRepository.findByUser_PlatoonId(user.getPlatoon().getId());
        for (UserReward item : list) {
            if (item.getAchievementState().equals(AchievementState.APPROVED) && item.getReward().getRewardType().equals(RewardType.LATH)) {
                rewards.add(buildUserReward(item));
            }
        }
        return rewards;
    }

    public List<PlatoonRewardDto> findAllStarRewards() {
        String email = userService.getAuthenticatedUserEmail();
        User user = userRepository.findByEmail(email);
        List<PlatoonRewardDto> rewards = new ArrayList<>();
        List<UserReward> list = userRewardRepository.findByUser_PlatoonId(user.getPlatoon().getId());
        for (UserReward item : list) {
            if (item.getAchievementState().equals(AchievementState.APPROVED) && item.getReward().getRewardType().equals(RewardType.STAR)) {
                rewards.add(buildUserReward(item));
            }
        }
        return rewards;
    }

    public List<PlatoonRewardDto> findAllTripRewards() {
        String email = userService.getAuthenticatedUserEmail();
        User user = userRepository.findByEmail(email);
        List<PlatoonRewardDto> rewards = new ArrayList<>();
        List<UserReward> list = userRewardRepository.findByUser_PlatoonId(user.getPlatoon().getId());
        for (UserReward item : list) {
            if (item.getAchievementState().equals(AchievementState.APPROVED) && item.getReward().getRewardType().equals(RewardType.TRIP)) {
                rewards.add(buildUserReward(item));
            }
        }
        return rewards;
    }

    public List<PlatoonRewardDto> findAllCampRewards() {
        String email = userService.getAuthenticatedUserEmail();
        User user = userRepository.findByEmail(email);
        List<PlatoonRewardDto> rewards = new ArrayList<>();
        List<UserReward> list = userRewardRepository.findByUser_PlatoonId(user.getPlatoon().getId());
        for (UserReward item : list) {
            if (item.getAchievementState().equals(AchievementState.APPROVED) && item.getReward().getRewardType().equals(RewardType.CAMP)) {
                rewards.add(buildUserReward(item));
            }
        }
        return rewards;
    }

    public PlatoonRewardDto buildUserReward(UserReward item) {
        PlatoonRewardDto platoonRewardDto = new PlatoonRewardDto();

        platoonRewardDto.setRewardId(item.getId());
        platoonRewardDto.setRewardType(item.getReward().getRewardType());
        return platoonRewardDto;
    }

    public PlatoonAchievementDto buildUserAchievement(Object item) {
        PlatoonAchievementDto platoonAchievementDto = new PlatoonAchievementDto();
        if (item instanceof UserTwelveYearAchievement) {
            platoonAchievementDto.setAchievementId(((UserTwelveYearAchievement) item).getId());
            platoonAchievementDto.setAchievementType(AchievementType.TWELWE_YEAR);

        } else if (item instanceof UserQuarterAchievement) {
            platoonAchievementDto.setAchievementId(((UserQuarterAchievement) item).getId());
            platoonAchievementDto.setAchievementType(AchievementType.QUARTER);

        } else if (item instanceof UserYearAchievement) {
            platoonAchievementDto.setAchievementId(((UserYearAchievement) item).getId());
            platoonAchievementDto.setAchievementType(AchievementType.YEAR);

        } else if (item instanceof UserThreeYearAchievement) {
            platoonAchievementDto.setAchievementId(((UserThreeYearAchievement) item).getId());
            platoonAchievementDto.setAchievementType(AchievementType.THREE_YEAR);

        }
        return platoonAchievementDto;
    }
}
