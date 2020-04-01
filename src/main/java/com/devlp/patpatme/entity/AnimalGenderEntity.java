package com.devlp.patpatme.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Entity
@Table(name = "animal_gender")
@Data
@Accessors(chain = true)
public class AnimalGenderEntity {

    @Id
    @SequenceGenerator(name = "animal_gender_generator", sequenceName = "animal_gender_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "animal_gender_generator")
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;
}
