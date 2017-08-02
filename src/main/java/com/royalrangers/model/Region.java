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
public class Region extends BaseModel {

    @JsonView(Views.Profile.class)
    @NotNull
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "region", cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    private Set<City> cities;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL, fetch=FetchType.LAZY)
    @JoinColumn(name = "country_id")
    private Country country;

    public Region() {}

    public Region(Country country, String name) {
        this.country = country;
        this.name = name;
    }

}
