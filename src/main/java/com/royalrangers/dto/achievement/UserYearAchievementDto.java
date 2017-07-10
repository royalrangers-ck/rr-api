package com.royalrangers.dto.achievement;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserYearAchievementDto {
    private Long yearId;
    private List<Long> quarterId;
    private Long sectionId;
    private List<Long> additionalTestIds;
}
