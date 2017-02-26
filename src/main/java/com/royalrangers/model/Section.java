package com.royalrangers.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "section")
public class Section {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Platoon platoon;

    @Column(nullable = false)
    private String name;

    public Section() {}

    public Section(Platoon platoon, String name) {
        this.platoon = platoon;
        this.name = name;
    }
}
