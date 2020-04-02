package com.devlp.patpatme.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Entity
@Table(name = "user_gender")
@Data
@Accessors(chain = true)
public class UserGenderEntity {

    @Id
    @SequenceGenerator(name = "user_gender_generator", sequenceName = "user_gender_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_gender_generator")
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;
}
