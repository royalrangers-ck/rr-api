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
public class Country extends BaseModel {

    @JsonView(Views.Profile.class)
    @NotNull
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "country", cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    private Set<Region> regions;

    public Country() {}

    public Country(String name) {
        this.name = name;
    }
}