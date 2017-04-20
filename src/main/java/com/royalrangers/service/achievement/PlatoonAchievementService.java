package com.royalrangers.service.achievement;

import com.royalrangers.dto.ResponseResult;
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
import com.royalrangers.utils.ResponseBuilder;
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

    public ResponseResult getTwelveYearAchievements() {
        List<PlatoonAchievementDto> result = new ArrayList<>();
        for (UserTwelveYearAchievement item : findAllTwelveYearAchievements()) {
            result.add(buildUserAchievement(item));
        }
        return ResponseBuilder.success(result);
    }

    public List<UserTwelveYearAchievement> findAllTwelveYearAchievements() {
        String email = userService.getAuthenticatedUserEmail();
        User user = userRepository.findByEmail(email);
        List<UserTwelveYearAchievement> list = userTwelveYearAchievementRepository.findAllByUser_PlatoonId(user.getPlatoon().getId());
        List<UserTwelveYearAchievement> result = new ArrayList<>();
        for (UserTwelveYearAchievement item : list) {
            if (item.getAchievementState().equals(AchievementState.APPROVED)) {
                result.add(item);
            }
        }
        return result;
    }

    public ResponseResult getThreeYearAchievements() {
        List<PlatoonAchievementDto> result = new ArrayList<>();
        for (UserThreeYearAchievement item : findAllThreeYearAchievements()) {
            result.add(buildUserAchievement(item));
        }
        return ResponseBuilder.success(result);
    }

    public List<UserThreeYearAchievement> findAllThreeYearAchievements() {
        String email = userService.getAuthenticatedUserEmail();
        User user = userRepository.findByEmail(email);
        List<UserThreeYearAchievement> list = userThreeYearAchievementRepository.findByUser_PlatoonId(user.getPlatoon().getId());
        List<UserThreeYearAchievement> result = new ArrayList<>();
        for (UserThreeYearAchievement item : list) {
            if (item.getAchievementState().equals(AchievementState.APPROVED)) {
                result.add(item);
            }
        }
        return result;
    }

    public ResponseResult getYearAchievements() {
        List<PlatoonAchievementDto> result = new ArrayList<>();
        for (UserYearAchievement item : findAllYearAchievements()) {
            result.add(buildUserAchievement(item));
        }
        return ResponseBuilder.success(result);
    }

    public List<UserYearAchievement> findAllYearAchievements() {
        String email = userService.getAuthenticatedUserEmail();
        User user = userRepository.findByEmail(email);
        List<UserYearAchievement> list = userYearAchievementRepository.findByUser_PlatoonId(user.getPlatoon().getId());
        List<UserYearAchievement> result = new ArrayList<>();
        for (UserYearAchievement item : list) {
            if (item.getAchievementState().equals(AchievementState.APPROVED)) {
                result.add(item);
            }
        }
        return result;
    }

    public ResponseResult getQuarterYearAchievements() {
        List<PlatoonAchievementDto> result = new ArrayList<>();
        for (UserQuarterAchievement item : findAllQuarterYearAchievements()) {
            result.add(buildUserAchievement(item));
        }
        return ResponseBuilder.success(result);
    }

    public List<UserQuarterAchievement> findAllQuarterYearAchievements() {
        String email = userService.getAuthenticatedUserEmail();
        User user = userRepository.findByEmail(email);
        List<UserQuarterAchievement> list = userQuarterAchievementRepository.findByUser_PlatoonId(user.getPlatoon().getId());
        List<UserQuarterAchievement> result = new ArrayList<>();
        for (UserQuarterAchievement item : list) {
            if (item.getAchievementState().equals(AchievementState.APPROVED)) {
                result.add(item);
            }
        }
        return result;
    }

    public List<UserReward> findAllMedalRewards() {
        String email = userService.getAuthenticatedUserEmail();
        User user = userRepository.findByEmail(email);
        List<UserReward> rewards = new ArrayList<>();
        List<UserReward> list = userRewardRepository.findByUser_PlatoonId(user.getPlatoon().getId());
        for (UserReward item : list) {
            if (item.getAchievementState().equals(AchievementState.APPROVED) && item.getReward().getRewardType().equals(RewardType.MEDAL)) {
                rewards.add(item);
            }
        }
        return rewards;
    }

    public ResponseResult getMedalRewards() {
        List<PlatoonRewardDto> rewards = new ArrayList<>();
        for (UserReward item : findAllMedalRewards()) {
            rewards.add(buildUserReward(item));
        }
        return ResponseBuilder.success(rewards);
    }

    public List<UserReward> findAllLathRewards() {
        String email = userService.getAuthenticatedUserEmail();
        User user = userRepository.findByEmail(email);
        List<UserReward> rewards = new ArrayList<>();
        List<UserReward> list = userRewardRepository.findByUser_PlatoonId(user.getPlatoon().getId());
        for (UserReward item : list) {
            if (item.getAchievementState().equals(AchievementState.APPROVED) && item.getReward().getRewardType().equals(RewardType.LATH)) {
                rewards.add(item);
            }
        }
        return rewards;
    }

    public ResponseResult getLathRewards() {
        List<PlatoonRewardDto> rewards = new ArrayList<>();
        for (UserReward item : findAllLathRewards()) {
            rewards.add(buildUserReward(item));
        }
        return ResponseBuilder.success(rewards);
    }

    public List<UserReward> findAllStarRewards() {
        String email = userService.getAuthenticatedUserEmail();
        User user = userRepository.findByEmail(email);
        List<UserReward> rewards = new ArrayList<>();
        List<UserReward> list = userRewardRepository.findByUser_PlatoonId(user.getPlatoon().getId());
        for (UserReward item : list) {
            if (item.getAchievementState().equals(AchievementState.APPROVED) && item.getReward().getRewardType().equals(RewardType.STAR)) {
                rewards.add(item);
            }
        }
        return rewards;
    }

    public ResponseResult getStarRewards() {
        List<PlatoonRewardDto> rewards = new ArrayList<>();
        for (UserReward item : findAllStarRewards()) {
            rewards.add(buildUserReward(item));
        }
        return ResponseBuilder.success(rewards);
    }

    public List<UserReward> findAllTripRewards() {
        String email = userService.getAuthenticatedUserEmail();
        User user = userRepository.findByEmail(email);
        List<UserReward> rewards = new ArrayList<>();
        List<UserReward> list = userRewardRepository.findByUser_PlatoonId(user.getPlatoon().getId());
        for (UserReward item : list) {
            if (item.getAchievementState().equals(AchievementState.APPROVED) && item.getReward().getRewardType().equals(RewardType.TRIP)) {
                rewards.add(item);
            }
        }
        return rewards;
    }

    public ResponseResult getTripRewards() {
        List<PlatoonRewardDto> rewards = new ArrayList<>();
        for (UserReward item : findAllTripRewards()) {
            rewards.add(buildUserReward(item));
        }
        return ResponseBuilder.success(rewards);
    }

    public List<UserReward> findAllCampRewards() {
        String email = userService.getAuthenticatedUserEmail();
        User user = userRepository.findByEmail(email);
        List<UserReward> rewards = new ArrayList<>();
        List<UserReward> list = userRewardRepository.findByUser_PlatoonId(user.getPlatoon().getId());
        for (UserReward item : list) {
            if (item.getAchievementState().equals(AchievementState.APPROVED) && item.getReward().getRewardType().equals(RewardType.CAMP)) {
                rewards.add(item);
            }
        }
        return rewards;
    }

    public ResponseResult getCampRewards() {
        List<PlatoonRewardDto> rewards = new ArrayList<>();
        for (UserReward item : findAllCampRewards()) {
            rewards.add(buildUserReward(item));
        }
        return ResponseBuilder.success(rewards);
    }

    public PlatoonRewardDto buildUserReward(UserReward item) {
        PlatoonRewardDto platoonRewardDto = new PlatoonRewardDto();

        platoonRewardDto.setRewardId(item.getId());
        platoonRewardDto.setRewardType(item.getReward().getRewardType());
        if (item.getReward().getRewardType().equals(RewardType.MEDAL)) {
            platoonRewardDto.setCount(findAllMedalRewards().size());
        } else if (item.getReward().getRewardType().equals(RewardType.LATH)) {
            platoonRewardDto.setCount(findAllLathRewards().size());
        } else if (item.getReward().getRewardType().equals(RewardType.STAR)) {
            platoonRewardDto.setCount(findAllStarRewards().size());
        } else if (item.getReward().getRewardType().equals(RewardType.CAMP)) {
            platoonRewardDto.setCount(findAllCampRewards().size());
        } else if (item.getReward().getRewardType().equals(RewardType.TRIP)) {
            platoonRewardDto.setCount(findAllTripRewards().size());
        }
        return platoonRewardDto;
    }

    public PlatoonAchievementDto buildUserAchievement(Object item) {
        PlatoonAchievementDto platoonAchievementDto = new PlatoonAchievementDto();
        if (item instanceof UserTwelveYearAchievement) {
            platoonAchievementDto.setAchievementId(((UserTwelveYearAchievement) item).getId());
            platoonAchievementDto.setAchievementType(AchievementType.TWELWE_YEAR);
            platoonAchievementDto.setCount(findAllTwelveYearAchievements().size());

        } else if (item instanceof UserQuarterAchievement) {
            platoonAchievementDto.setAchievementId(((UserQuarterAchievement) item).getId());
            platoonAchievementDto.setAchievementType(AchievementType.QUARTER);
            platoonAchievementDto.setCount(findAllQuarterYearAchievements().size());

        } else if (item instanceof UserYearAchievement) {
            platoonAchievementDto.setAchievementId(((UserYearAchievement) item).getId());
            platoonAchievementDto.setAchievementType(AchievementType.YEAR);
            platoonAchievementDto.setCount(findAllYearAchievements().size());

        } else if (item instanceof UserThreeYearAchievement) {
            platoonAchievementDto.setAchievementId(((UserThreeYearAchievement) item).getId());
            platoonAchievementDto.setAchievementType(AchievementType.THREE_YEAR);
            platoonAchievementDto.setCount(findAllThreeYearAchievements().size());

        }
        return platoonAchievementDto;
    }

    public ResponseResult getInProgressTwelveYearAchievements() {
        List<PlatoonAchievementDto> result = new ArrayList<>();
        for (UserTwelveYearAchievement item : findAllInProgressTwelveYearAchievements()) {
            result.add(buildUserAchievement(item));
        }
        return ResponseBuilder.success(result);
    }

    public List<UserTwelveYearAchievement> findAllInProgressTwelveYearAchievements() {
        String email = userService.getAuthenticatedUserEmail();
        User user = userRepository.findByEmail(email);
        List<UserTwelveYearAchievement> list = userTwelveYearAchievementRepository.findAllByUser_PlatoonId(user.getPlatoon().getId());
        List<UserTwelveYearAchievement> result = new ArrayList<>();
        for (UserTwelveYearAchievement item : list) {
            if (item.getAchievementState().equals(AchievementState.IN_PROGRESS)) {
                result.add(item);
            }
        }
        return result;
    }

    public ResponseResult getInProgressThreeYearAchievements() {
        List<PlatoonAchievementDto> result = new ArrayList<>();
        for (UserThreeYearAchievement item : findAllThreeYearAchievements()) {
            result.add(buildUserAchievement(item));
        }
        return ResponseBuilder.success(result);
    }

    public List<UserThreeYearAchievement> findAllInProgressThreeYearAchievements() {
        String email = userService.getAuthenticatedUserEmail();
        User user = userRepository.findByEmail(email);
        List<UserThreeYearAchievement> list = userThreeYearAchievementRepository.findByUser_PlatoonId(user.getPlatoon().getId());
        List<UserThreeYearAchievement> result = new ArrayList<>();
        for (UserThreeYearAchievement item : list) {
            if (item.getAchievementState().equals(AchievementState.IN_PROGRESS)) {
                result.add(item);
            }
        }
        return result;
    }

    public ResponseResult getInProgressYearAchievements() {
        List<PlatoonAchievementDto> result = new ArrayList<>();
        for (UserYearAchievement item : findAllYearAchievements()) {
            result.add(buildUserAchievement(item));
        }
        return ResponseBuilder.success(result);
    }

    public List<UserYearAchievement> findAllInProgressYearAchievements() {
        String email = userService.getAuthenticatedUserEmail();
        User user = userRepository.findByEmail(email);
        List<UserYearAchievement> list = userYearAchievementRepository.findByUser_PlatoonId(user.getPlatoon().getId());
        List<UserYearAchievement> result = new ArrayList<>();
        for (UserYearAchievement item : list) {
            if (item.getAchievementState().equals(AchievementState.IN_PROGRESS)) {
                result.add(item);
            }
        }
        return result;
    }

    public ResponseResult getInProgressQuarterYearAchievements() {
        List<PlatoonAchievementDto> result = new ArrayList<>();
        for (UserQuarterAchievement item : findAllQuarterYearAchievements()) {
            result.add(buildUserAchievement(item));
        }
        return ResponseBuilder.success(result);
    }

    public List<UserQuarterAchievement> findAllInProgressQuarterYearAchievements() {
        String email = userService.getAuthenticatedUserEmail();
        User user = userRepository.findByEmail(email);
        List<UserQuarterAchievement> list = userQuarterAchievementRepository.findByUser_PlatoonId(user.getPlatoon().getId());
        List<UserQuarterAchievement> result = new ArrayList<>();
        for (UserQuarterAchievement item : list) {
            if (item.getAchievementState().equals(AchievementState.IN_PROGRESS)) {
                result.add(item);
            }
        }
        return result;
    }

    public List<UserReward> findAllInProgressMedalRewards() {
        String email = userService.getAuthenticatedUserEmail();
        User user = userRepository.findByEmail(email);
        List<UserReward> rewards = new ArrayList<>();
        List<UserReward> list = userRewardRepository.findByUser_PlatoonId(user.getPlatoon().getId());
        for (UserReward item : list) {
            if (item.getAchievementState().equals(AchievementState.IN_PROGRESS) && item.getReward().getRewardType().equals(RewardType.MEDAL)) {
                rewards.add(item);
            }
        }
        return rewards;
    }

    public ResponseResult getInProgressMedalRewards() {
        List<PlatoonRewardDto> rewards = new ArrayList<>();
        for (UserReward item : findAllMedalRewards()) {
            rewards.add(buildUserReward(item));
        }
        return ResponseBuilder.success(rewards);
    }

    public List<UserReward> findAllInProgressLathRewards() {
        String email = userService.getAuthenticatedUserEmail();
        User user = userRepository.findByEmail(email);
        List<UserReward> rewards = new ArrayList<>();
        List<UserReward> list = userRewardRepository.findByUser_PlatoonId(user.getPlatoon().getId());
        for (UserReward item : list) {
            if (item.getAchievementState().equals(AchievementState.IN_PROGRESS) && item.getReward().getRewardType().equals(RewardType.LATH)) {
                rewards.add(item);
            }
        }
        return rewards;
    }

    public ResponseResult getInProgressLathRewards() {
        List<PlatoonRewardDto> rewards = new ArrayList<>();
        for (UserReward item : findAllLathRewards()) {
            rewards.add(buildUserReward(item));
        }
        return ResponseBuilder.success(rewards);
    }

    public List<UserReward> findAllInProgressStarRewards() {
        String email = userService.getAuthenticatedUserEmail();
        User user = userRepository.findByEmail(email);
        List<UserReward> rewards = new ArrayList<>();
        List<UserReward> list = userRewardRepository.findByUser_PlatoonId(user.getPlatoon().getId());
        for (UserReward item : list) {
            if (item.getAchievementState().equals(AchievementState.IN_PROGRESS) && item.getReward().getRewardType().equals(RewardType.STAR)) {
                rewards.add(item);
            }
        }
        return rewards;
    }

    public ResponseResult getInProgressStarRewards() {
        List<PlatoonRewardDto> rewards = new ArrayList<>();
        for (UserReward item : findAllStarRewards()) {
            rewards.add(buildUserReward(item));
        }
        return ResponseBuilder.success(rewards);
    }

    public List<UserReward> findAllInProgressTripRewards() {
        String email = userService.getAuthenticatedUserEmail();
        User user = userRepository.findByEmail(email);
        List<UserReward> rewards = new ArrayList<>();
        List<UserReward> list = userRewardRepository.findByUser_PlatoonId(user.getPlatoon().getId());
        for (UserReward item : list) {
            if (item.getAchievementState().equals(AchievementState.IN_PROGRESS) && item.getReward().getRewardType().equals(RewardType.TRIP)) {
                rewards.add(item);
            }
        }
        return rewards;
    }

    public ResponseResult getInProgressTripRewards() {
        List<PlatoonRewardDto> rewards = new ArrayList<>();
        for (UserReward item : findAllTripRewards()) {
            rewards.add(buildUserReward(item));
        }
        return ResponseBuilder.success(rewards);
    }

    public List<UserReward> findAllInProgressCampRewards() {
        String email = userService.getAuthenticatedUserEmail();
        User user = userRepository.findByEmail(email);
        List<UserReward> rewards = new ArrayList<>();
        List<UserReward> list = userRewardRepository.findByUser_PlatoonId(user.getPlatoon().getId());
        for (UserReward item : list) {
            if (item.getAchievementState().equals(AchievementState.IN_PROGRESS) && item.getReward().getRewardType().equals(RewardType.CAMP)) {
                rewards.add(item);
            }
        }
        return rewards;
    }

    public ResponseResult getInProgressCampRewards() {
        List<PlatoonRewardDto> rewards = new ArrayList<>();
        for (UserReward item : findAllCampRewards()) {
            rewards.add(buildUserReward(item));
        }
        return ResponseBuilder.success(rewards);
    }
}
