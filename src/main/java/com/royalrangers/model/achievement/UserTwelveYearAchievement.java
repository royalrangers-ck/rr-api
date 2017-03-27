package com.royalrangers.model.achievement;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.royalrangers.enums.achivement.AchievementStatus;
import com.royalrangers.model.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
public class UserTwelveYearAchievement {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private Date createDate;

    @Column
    private Date updateDate;

    @Enumerated
    private AchievementStatus achievementStatus;

    @Column
    private Long userId;

    @Column
    private Long reviewerId;

    @Column
    private Long twelveYearAchievementId;
}
