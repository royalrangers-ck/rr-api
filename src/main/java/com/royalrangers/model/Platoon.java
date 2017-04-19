package com.royalrangers.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@Entity
public class Platoon extends BaseModel{

    @JsonView(Views.Profile.class)
    @NotNull
    private String name;

    @JsonView(Views.Public.class)
    private String history;

    @JsonView(Views.Public.class)
    private String logoUrl;

    @JsonView(Views.Public.class)
    private String address;

    @JsonView(Views.Public.class)
    private Date meetTime;

    @JsonView(Views.Public.class)
    private String city;

    @JsonIgnore
    @OneToMany(mappedBy = "platoon", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Section> sections;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id")
    private Group group;

    public Platoon() {}

    public Platoon(String name, String history, String logoUrl, String address, Date meetTime, String city, Set<Section> sections, Group group) {
        this.name = name;
        this.history = history;
        this.logoUrl = logoUrl;
        this.address = address;
        this.meetTime = meetTime;
        this.city = city;
        this.sections = sections;
        this.group = group;
    }

    public Platoon(Group group, String name) {
        this.group = group;
        this.name = name;
    }
}
