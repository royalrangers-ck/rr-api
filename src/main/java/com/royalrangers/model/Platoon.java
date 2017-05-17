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
    private Integer name;

    @JsonView(Views.Public.class)
    private String history;

    @JsonView(Views.Public.class)
    private String logoUrl;

    @JsonView(Views.Public.class)
    private String address;

    @JsonView(Views.Public.class)
    private Date meetTime;

    //    TODO What is this?
//    @JsonView(Views.Public.class)
//    private String city;

    @JsonIgnore
    @OneToMany(mappedBy = "platoon", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Section> sections;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id")
    private City city;

    public Platoon() {}

    public Platoon(City city, Integer name) {
        this.city = city;
        this.name = name;
    }
}
