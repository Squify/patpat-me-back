package com.devlp.patpatme.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Entity
@Table(name = "animal_type")
@Data
@Accessors(chain = true)
public class AnimalTypeEntity {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;
}
