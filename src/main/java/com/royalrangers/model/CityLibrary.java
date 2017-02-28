package com.royalrangers.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class CityLibrary {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id")
    private Country country;

    @Column(nullable = false)
    private String name;

    public CityLibrary() {
    }

    public CityLibrary(Country country, String name) {
        this.country = country;
        this.name = name;
    }
}
