package com.royalrangers.model.achievements;

import com.royalrangers.model.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@MappedSuperclass
public abstract class Achievement {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 50)
    @NotNull
    private String name;

    @Column
    @NotNull
    private String description;

    @Column
    private String logoUrl;

    @Column(length = 150)
    @NotNull
    private String requirements;



}
