package com.royalrangers.model.achievement;

import com.fasterxml.jackson.annotation.JsonView;
import com.royalrangers.model.Views;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class RoadMapQuarterAchievement {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long sectionId;

    @OneToOne
    private QuarterAchievement quarterAchievement;

    @JsonView(Views.Achievement.class)
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "RoadMapQuarter_Tests",
            joinColumns = {@JoinColumn(name = "RoadMapQuarterAchievement_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "test_id", referencedColumnName = "id")})
    private List<Test> tests;
}
