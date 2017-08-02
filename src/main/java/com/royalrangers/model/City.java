package com.royalrangers.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Getter
@Setter
@Entity
public class City extends BaseModel {

    @JsonView(Views.Profile.class)
    @NotNull
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    private Set<Platoon> platoons;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id")
    private Region region;

    public City(){}

    public City(Region region, String name) {
        this.region = region;
        this.name = name;
    }

}
