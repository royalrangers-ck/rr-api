package com.royalrangers.model.achievement;

import com.royalrangers.enums.achivement.AchievementStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
public class UserTest {
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
    private Long testId;
}
