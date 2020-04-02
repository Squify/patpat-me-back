package com.devlp.patpatme.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Entity
@Table(name = "race")
@Data
@Accessors(chain = true)
public class RaceEntity {

    @Id
    @SequenceGenerator(name = "race_generator", sequenceName = "race_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "race_generator")
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_id_type")
    private AnimalTypeEntity type;
}
