package com.royalrangers.dto.achievement;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ThreeYearRequestDto {
    private String name;
    private String description;
    private String requirements;
    private String logoUrl;
    private Integer upLevelId;
    private String ageCategory;
}
