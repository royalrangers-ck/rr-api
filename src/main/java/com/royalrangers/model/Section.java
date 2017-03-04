package com.royalrangers.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Section {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "platoon_id")
    private Platoon platoon;

    @Column(nullable = false)
    private String name;

    public Section() {}

    public Section(Platoon platoon, String name) {
        this.platoon = platoon;
        this.name = name;
    }
}
