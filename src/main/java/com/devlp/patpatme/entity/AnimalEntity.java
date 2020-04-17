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
    @SequenceGenerator(name = "users_generator", sequenceName = "users_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_generator")
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "birthday")
    private Timestamp birthday;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_id_owner")
    private UserEntity owner;

    /*@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_id_gender")
    private AnimalGenderEntity gender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_id_type")
    private AnimalTypeEntity type;

     @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_id_race")
    private RaceEntity race;*/

    @JoinColumn(name = "fk_id_gender")
    private Integer gender;

    @JoinColumn(name = "fk_id_type")
    private Integer type;

    @JoinColumn(name = "fk_id_race")
    private Integer race;


}
