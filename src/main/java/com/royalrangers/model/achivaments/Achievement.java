package com.royalrangers.model.achivaments;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Getter
@Setter
@Entity
public class Achievement {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 50)
    @NotNull
    private String name;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "achievement")
    private Set<Task> tasks;

    @Column
    @NotNull
    private String description;

    @Column
    private String logoUrl;

    @Column(length = 150)
    @NotNull
    private String requirements;
}
