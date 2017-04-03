package com.royalrangers.bean.achievement;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserAchievementBean {
    private Long id;
    @JsonIgnore
    private String email;
    private String firstName;
    private String lastName;
    @JsonIgnore
    private Long platoonId;
}
