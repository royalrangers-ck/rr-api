package com.royalrangers.service.achievement;

import com.royalrangers.dto.achievement.PlatoonAchievementDto;
import com.royalrangers.dto.achievement.PlatoonRewardDto;
import com.royalrangers.enums.achivement.AchievementState;
import com.royalrangers.enums.achivement.AchievementType;
import com.royalrangers.enums.achivement.RewardType;
import com.royalrangers.model.achievement.*;
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
    private UserService userService;

    public List<PlatoonAchievementDto> getApprovedTwelveYearAchievements() {
        List<PlatoonAchievementDto> result = new ArrayList<>();
        for (UserTwelveYearAchievement item : findApprovedTwelveYearAchievementsByPlatoon()) {
            result.add(buildUserAchievement(item));
        }

        return result;
    }

    public List<UserTwelveYearAchievement> findApprovedTwelveYearAchievementsByPlatoon() {
        return userTwelveYearAchievementRepository.findAllByUserPlatoonIdAndAchievementState(userService.getAuthenticatedUser().getPlatoon().getId(), AchievementState.APPROVED);
    }

    public List<PlatoonAchievementDto> getApprovedThreeYearAchievements() {
        List<PlatoonAchievementDto> result = new ArrayList<>();
        for (UserThreeYearAchievement item : findApprovedThreeYearAchievementsByPlatoon()) {
            result.add(buildUserAchievement(item));
        }

        return result;
    }

    public List<UserThreeYearAchievement> findApprovedThreeYearAchievementsByPlatoon() {
        return userThreeYearAchievementRepository.findByUserPlatoonIdAndAchievementState(userService.getAuthenticatedUser().getPlatoon().getId(), AchievementState.APPROVED);
    }

    public List<PlatoonAchievementDto> getApprovedYearAchievements() {
        List<PlatoonAchievementDto> result = new ArrayList<>();
        for (UserYearAchievement item : findApprovedYearAchievementsByPlatoon()) {
            result.add(buildUserAchievement(item));
        }

        return result;
    }

    public List<UserYearAchievement> findApprovedYearAchievementsByPlatoon() {
        return userYearAchievementRepository.findByUserPlatoonIdAndAchievementState(userService.getAuthenticatedUser().getPlatoon().getId(), AchievementState.APPROVED);
    }

    public List<PlatoonAchievementDto> getApprovedQuarterAchievements() {
        List<PlatoonAchievementDto> result = new ArrayList<>();
        for (UserQuarterAchievement item : findApprovedQuarterAchievementsByPlatoon()) {
            result.add(buildUserAchievement(item));
        }

        return result;
    }

    public List<UserQuarterAchievement> findApprovedQuarterAchievementsByPlatoon() {
        return userQuarterAchievementRepository.findByUserPlatoonIdAndAchievementState(userService.getAuthenticatedUser().getPlatoon().getId(), AchievementState.APPROVED);
    }

    public List<UserReward> findApprovedMedalRewardsByPlatoon() {
        return userRewardRepository.findByUserPlatoonIdAndAchievementStateAndReward_RewardType(userService.getAuthenticatedUser().getPlatoon().getId(), AchievementState.APPROVED, RewardType.MEDAL);
    }

    public List<PlatoonRewardDto> getApprovedMedalRewards() {
        List<PlatoonRewardDto> rewards = new ArrayList<>();
        for (UserReward item : findApprovedMedalRewardsByPlatoon()) {
            rewards.add(buildUserReward(item));
        }

        return rewards;
    }

    public List<UserReward> findApprovedLathRewardsByPlatoon() {
        return userRewardRepository.findByUserPlatoonIdAndAchievementStateAndReward_RewardType(userService.getAuthenticatedUser().getPlatoon().getId(), AchievementState.APPROVED, RewardType.LATH);
    }

    public List<PlatoonRewardDto> getApprovedLathRewards() {
        List<PlatoonRewardDto> rewards = new ArrayList<>();
        for (UserReward item : findApprovedLathRewardsByPlatoon()) {
            rewards.add(buildUserReward(item));
        }

        return rewards;
    }

    public List<UserReward> findApprovedStarRewardsByPlatoon() {
        return userRewardRepository.findByUserPlatoonIdAndAchievementStateAndReward_RewardType(userService.getAuthenticatedUser().getPlatoon().getId(), AchievementState.APPROVED, RewardType.STAR);
    }

    public List<PlatoonRewardDto> getApprovedStarRewards() {
        List<PlatoonRewardDto> rewards = new ArrayList<>();
        for (UserReward item : findApprovedStarRewardsByPlatoon()) {
            rewards.add(buildUserReward(item));
        }

        return rewards;
    }

    public List<UserReward> findApprovedTripRewardsByPlatoon() {
        return userRewardRepository.findByUserPlatoonIdAndAchievementStateAndReward_RewardType(userService.getAuthenticatedUser().getPlatoon().getId(), AchievementState.APPROVED, RewardType.TRIP);
    }

    public List<PlatoonRewardDto> getApprovedTripRewards() {
        List<PlatoonRewardDto> rewards = new ArrayList<>();
        for (UserReward item : findApprovedTripRewardsByPlatoon()) {
            rewards.add(buildUserReward(item));
        }

        return rewards;
    }

    public List<UserReward> findApprovedCampRewardsByPlatoon() {
        return userRewardRepository.findByUserPlatoonIdAndAchievementStateAndReward_RewardType(userService.getAuthenticatedUser().getPlatoon().getId(), AchievementState.APPROVED, RewardType.CAMP);
    }

    public List<PlatoonRewardDto> getApprovedCampRewards() {
        List<PlatoonRewardDto> rewards = new ArrayList<>();
        for (UserReward item : findApprovedCampRewardsByPlatoon()) {
            rewards.add(buildUserReward(item));
        }

        return rewards;
    }

    public PlatoonRewardDto buildUserReward(UserReward item) {
        PlatoonRewardDto platoonRewardDto = new PlatoonRewardDto();

        platoonRewardDto.setRewardId(item.getId());
        platoonRewardDto.setRewardType(item.getReward().getRewardType());
        if (item.getReward().getRewardType().equals(RewardType.MEDAL) && item.getAchievementState().equals(AchievementState.APPROVED)) {
            platoonRewardDto.setCount(findApprovedMedalRewardsByPlatoon().size());
        } else if (item.getReward().getRewardType().equals(RewardType.MEDAL) && item.getAchievementState().equals(AchievementState.IN_PROGRESS)) {
            platoonRewardDto.setCount(findInProgressMedalRewardsByPlatoon().size());
        } else if (item.getReward().getRewardType().equals(RewardType.LATH) && item.getAchievementState().equals(AchievementState.APPROVED)) {
            platoonRewardDto.setCount(findApprovedLathRewardsByPlatoon().size());
        } else if (item.getReward().getRewardType().equals(RewardType.LATH) && item.getAchievementState().equals(AchievementState.IN_PROGRESS)) {
            platoonRewardDto.setCount(findInProgressLathRewardsByPlatoon().size());
        } else if (item.getReward().getRewardType().equals(RewardType.STAR) && item.getAchievementState().equals(AchievementState.APPROVED)) {
            platoonRewardDto.setCount(findApprovedStarRewardsByPlatoon().size());
        } else if (item.getReward().getRewardType().equals(RewardType.STAR) && item.getAchievementState().equals(AchievementState.IN_PROGRESS)) {
            platoonRewardDto.setCount(findInProgressStarRewardsByPlatoon().size());
        } else if (item.getReward().getRewardType().equals(RewardType.CAMP) && item.getAchievementState().equals(AchievementState.APPROVED)) {
            platoonRewardDto.setCount(findApprovedCampRewardsByPlatoon().size());
        } else if (item.getReward().getRewardType().equals(RewardType.CAMP) && item.getAchievementState().equals(AchievementState.IN_PROGRESS)) {
            platoonRewardDto.setCount(findInProgressCampRewardsByPlatoon().size());
        } else if (item.getReward().getRewardType().equals(RewardType.TRIP) && item.getAchievementState().equals(AchievementState.APPROVED)) {
            platoonRewardDto.setCount(findApprovedTripRewardsByPlatoon().size());
        } else if (item.getReward().getRewardType().equals(RewardType.TRIP) && item.getAchievementState().equals(AchievementState.IN_PROGRESS)) {
            platoonRewardDto.setCount(findInProgressTripRewardsByPlatoon().size());
        }

        return platoonRewardDto;
    }

    public PlatoonAchievementDto buildUserAchievement(Object item) {
        PlatoonAchievementDto platoonAchievementDto = new PlatoonAchievementDto();
        if (item instanceof UserTwelveYearAchievement) {
            platoonAchievementDto.setAchievementId(((UserTwelveYearAchievement) item).getId());
            platoonAchievementDto.setAchievementType(AchievementType.TWELWE_YEAR);
            if (((UserTwelveYearAchievement) item).getAchievementState().equals(AchievementState.APPROVED)) {
                platoonAchievementDto.setCount(findApprovedTwelveYearAchievementsByPlatoon().size());
            } else if (((UserTwelveYearAchievement) item).getAchievementState().equals(AchievementState.IN_PROGRESS)) {
                platoonAchievementDto.setCount(findInProgressTwelveYearAchievements().size());
            }

        } else if (item instanceof UserQuarterAchievement) {
            platoonAchievementDto.setAchievementId(((UserQuarterAchievement) item).getId());
            platoonAchievementDto.setAchievementType(AchievementType.QUARTER);
            platoonAchievementDto.setCount(findApprovedQuarterAchievementsByPlatoon().size());
            if (((UserQuarterAchievement) item).getAchievementState().equals(AchievementState.APPROVED)) {
                platoonAchievementDto.setCount(findApprovedQuarterAchievementsByPlatoon().size());
            } else if (((UserQuarterAchievement) item).getAchievementState().equals(AchievementState.IN_PROGRESS)) {
                platoonAchievementDto.setCount(findInProgressQuarterAchievements().size());
            }

        } else if (item instanceof UserYearAchievement) {
            platoonAchievementDto.setAchievementId(((UserYearAchievement) item).getId());
            platoonAchievementDto.setAchievementType(AchievementType.YEAR);
            platoonAchievementDto.setCount(findApprovedYearAchievementsByPlatoon().size());
            if (((UserYearAchievement) item).getAchievementState().equals(AchievementState.APPROVED)) {
                platoonAchievementDto.setCount(findApprovedYearAchievementsByPlatoon().size());
            } else if (((UserYearAchievement) item).getAchievementState().equals(AchievementState.IN_PROGRESS)) {
                platoonAchievementDto.setCount(findInProgressYearAchievements().size());
            }

        } else if (item instanceof UserThreeYearAchievement) {
            platoonAchievementDto.setAchievementId(((UserThreeYearAchievement) item).getId());
            platoonAchievementDto.setAchievementType(AchievementType.THREE_YEAR);
            platoonAchievementDto.setCount(findApprovedThreeYearAchievementsByPlatoon().size());
            if (((UserThreeYearAchievement) item).getAchievementState().equals(AchievementState.APPROVED)) {
                platoonAchievementDto.setCount(findApprovedThreeYearAchievementsByPlatoon().size());
            } else if (((UserThreeYearAchievement) item).getAchievementState().equals(AchievementState.IN_PROGRESS)) {
                platoonAchievementDto.setCount(findInProgressThreeYearAchievements().size());
            }
        }

        return platoonAchievementDto;
    }

    public List<PlatoonAchievementDto> getInProgressTwelveYearAchievements() {
        List<PlatoonAchievementDto> result = new ArrayList<>();
        for (UserTwelveYearAchievement item : findInProgressTwelveYearAchievements()) {
            result.add(buildUserAchievement(item));
        }

        return result;
    }

    public List<UserTwelveYearAchievement> findInProgressTwelveYearAchievements() {
        return userTwelveYearAchievementRepository.findAllByUserPlatoonIdAndAchievementState(userService.getAuthenticatedUser().getPlatoon().getId(), AchievementState.IN_PROGRESS);
    }

    public List<PlatoonAchievementDto> getInProgressThreeYearAchievements() {
        List<PlatoonAchievementDto> result = new ArrayList<>();
        for (UserThreeYearAchievement item : findInProgressThreeYearAchievements()) {
            result.add(buildUserAchievement(item));
        }

        return result;
    }

    public List<UserThreeYearAchievement> findInProgressThreeYearAchievements() {
        return userThreeYearAchievementRepository.findByUserPlatoonIdAndAchievementState(userService.getAuthenticatedUser().getPlatoon().getId(), AchievementState.IN_PROGRESS);
    }

    public List<PlatoonAchievementDto> getInProgressYearAchievements() {
        List<PlatoonAchievementDto> result = new ArrayList<>();
        for (UserYearAchievement item : findInProgressYearAchievements()) {
            result.add(buildUserAchievement(item));
        }

        return result;
    }

    public List<UserYearAchievement> findInProgressYearAchievements() {
        return userYearAchievementRepository.findByUserPlatoonIdAndAchievementState(userService.getAuthenticatedUser().getPlatoon().getId(), AchievementState.IN_PROGRESS);
    }

    public List<PlatoonAchievementDto> getInProgressQuarterAchievements() {
        List<PlatoonAchievementDto> result = new ArrayList<>();
        for (UserQuarterAchievement item : findInProgressQuarterAchievements()) {
            result.add(buildUserAchievement(item));
        }

        return result;
    }

    public List<UserQuarterAchievement> findInProgressQuarterAchievements() {
        return userQuarterAchievementRepository.findByUserPlatoonIdAndAchievementState(userService.getAuthenticatedUser().getPlatoon().getId(), AchievementState.IN_PROGRESS);
    }

    public List<UserReward> findInProgressMedalRewardsByPlatoon() {
        return userRewardRepository.findByUserPlatoonIdAndAchievementStateAndReward_RewardType(userService.getAuthenticatedUser().getPlatoon().getId(), AchievementState.IN_PROGRESS, RewardType.MEDAL);
    }

    public List<PlatoonRewardDto> getInProgressMedalRewards() {
        List<PlatoonRewardDto> rewards = new ArrayList<>();
        for (UserReward item : findInProgressMedalRewardsByPlatoon()) {
            rewards.add(buildUserReward(item));
        }

        return rewards;
    }

    public List<UserReward> findInProgressLathRewardsByPlatoon() {
        return userRewardRepository.findByUserPlatoonIdAndAchievementStateAndReward_RewardType(userService.getAuthenticatedUser().getPlatoon().getId(), AchievementState.IN_PROGRESS, RewardType.LATH);
    }

    public List<PlatoonRewardDto> getInProgressLathRewards() {
        List<PlatoonRewardDto> rewards = new ArrayList<>();
        for (UserReward item : findInProgressLathRewardsByPlatoon()) {
            rewards.add(buildUserReward(item));
        }

        return rewards;
    }

    public List<UserReward> findInProgressStarRewardsByPlatoon() {
        return userRewardRepository.findByUserPlatoonIdAndAchievementStateAndReward_RewardType(userService.getAuthenticatedUser().getPlatoon().getId(), AchievementState.IN_PROGRESS, RewardType.STAR);
    }

    public List<PlatoonRewardDto> getInProgressStarRewards() {
        List<PlatoonRewardDto> rewards = new ArrayList<>();
        for (UserReward item : findInProgressStarRewardsByPlatoon()) {
            rewards.add(buildUserReward(item));
        }

        return rewards;
    }

    public List<UserReward> findInProgressTripRewardsByPlatoon() {
        return userRewardRepository.findByUserPlatoonIdAndAchievementStateAndReward_RewardType(userService.getAuthenticatedUser().getPlatoon().getId(), AchievementState.IN_PROGRESS, RewardType.TRIP);
    }

    public List<PlatoonRewardDto> getInProgressTripRewards() {
        List<PlatoonRewardDto> rewards = new ArrayList<>();
        for (UserReward item : findInProgressTripRewardsByPlatoon()) {
            rewards.add(buildUserReward(item));
        }

        return rewards;
    }

    public List<UserReward> findInProgressCampRewardsByPlatoon() {
        return userRewardRepository.findByUserPlatoonIdAndAchievementStateAndReward_RewardType(userService.getAuthenticatedUser().getPlatoon().getId(), AchievementState.IN_PROGRESS, RewardType.CAMP);
    }

    public List<PlatoonRewardDto> getInProgressCampRewards() {
        List<PlatoonRewardDto> rewards = new ArrayList<>();
        for (UserReward item : findInProgressCampRewardsByPlatoon()) {
            rewards.add(buildUserReward(item));
        }

        return rewards;
    }
}
