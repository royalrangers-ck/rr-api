package com.royalrangers.service.achievement;

import com.dropbox.core.DbxException;
import com.royalrangers.enums.ImageType;
import com.royalrangers.repository.achievement.RewardRepository;
import com.royalrangers.model.achievement.Reward;
import com.royalrangers.service.DropboxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RewardService {

    @Autowired
    private RewardRepository rewardRepository;

    @Autowired
    private DropboxService dropboxService;

    public List<Reward> getAllReward() {
        return rewardRepository.findAll();
    }

    public Reward addReward(Reward reward) {
        return rewardRepository.saveAndFlush(reward);
    }

    public Reward getRewardById(Long id) {
        return rewardRepository.findOne(id);
    }

    public void deleteReward(Long id) {
        rewardRepository.delete(id);
    }

    public Reward editReward(Reward reward, Long rewardId) {
        Reward editReward = getRewardById(rewardId);
        editReward.setName(reward.getName());
        editReward.setDescription(reward.getDescription());
        editReward.setLogoUrl(reward.getLogoUrl());
        editReward.setUpdateDate(new Date());
        editReward.setRewardMark(reward.getRewardMark());
        editReward.setRewardType(reward.getRewardType());
        return rewardRepository.saveAndFlush(editReward);
    }

    public void setLogoUrl(String avatarUrl, Long rewardId) throws DbxException {
        Reward editReward = rewardRepository.findOne(rewardId);
        if (editReward.getLogoUrl() != null) {
            dropboxService.deleteImage(editReward.getLogoUrl(), ImageType.REWARD_LOGO);
        }
        editReward.setLogoUrl(avatarUrl);
        rewardRepository.saveAndFlush(editReward);
    }

    public void deleteLogo(Long rewardId) throws DbxException {
        Reward reward = rewardRepository.findOne(rewardId);
        if (reward.getLogoUrl() != null) {
            dropboxService.deleteImage(reward.getLogoUrl(), ImageType.REWARD_LOGO);
        }
        reward.setLogoUrl(null);
        rewardRepository.saveAndFlush(reward);
    }

}
