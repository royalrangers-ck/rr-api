package com.royalrangers.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
public class Country {
    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(mappedBy = "country", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<City> city;

    private String name;

    public Country() {}

    public Country(String name) {
        this.name = name;
    }
}
