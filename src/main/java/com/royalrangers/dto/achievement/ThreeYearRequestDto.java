package com.royalrangers.dto.achievement;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ThreeYearRequestDto {
    private String name;
    private String description;
    private String logoUrl;
    private String ageCategory;
    private Integer upLevelId;
}
