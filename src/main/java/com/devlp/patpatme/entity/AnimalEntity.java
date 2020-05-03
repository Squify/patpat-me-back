package com.devlp.patpatme.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "animal")
@Data
@Accessors(chain = true)
public class AnimalEntity {

    @Id
    @SequenceGenerator(name = "animal_generator", sequenceName = "animal_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "animal_generator")
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "birthday")
    private Timestamp birthday;

    @ManyToOne
    @JoinColumn(name = "fk_id_owner")
    private UserEntity owner;

    @ManyToOne
    @JoinColumn(name = "fk_id_gender")
    private AnimalGenderEntity gender;

    @ManyToOne
    @JoinColumn(name = "fk_id_type")
    private AnimalTypeEntity type;

    @ManyToOne
    @JoinColumn(name = "fk_id_race")
    private RaceEntity race;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_id_temper")
    private AnimalTemperEntity temper;
}
