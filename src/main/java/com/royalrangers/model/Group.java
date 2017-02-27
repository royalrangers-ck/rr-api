package com.royalrangers.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
public class Group {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id")
    private City city;

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL, fetch=FetchType.LAZY)
    private Set<Platoon> platoons;

    @Column(nullable = false)
    private String name;

    public Group() {
    }

    public Group(City city, String name) {
        this.city = city;
        this.name = name;
    }
}
