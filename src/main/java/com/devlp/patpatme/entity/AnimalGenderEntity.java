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
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;
}
