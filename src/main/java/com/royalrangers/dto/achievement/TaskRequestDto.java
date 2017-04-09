package com.royalrangers.dto.achievement;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskRequestDto {

    private String name;
    private String description;
    private Integer testId;
}
