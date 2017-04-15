package com.royalrangers.dto.achievement;

import com.royalrangers.enums.achivement.AchievementType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlatoonAchievementDto {
    private Long achievementId;
    private AchievementType achievementType;
    private int count;
}
