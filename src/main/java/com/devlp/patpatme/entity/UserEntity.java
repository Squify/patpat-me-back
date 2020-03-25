package com.devlp.patpatme.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "users")
@Data
@Accessors(chain = true)
public class UserEntity {

    @Id
    @SequenceGenerator(name = "users_generator", sequenceName = "users_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_generator")
    @Column(name = "id")
    private Number id;

    @Column(name = "email")
    private String email;

    @Column(name = "pseudo")
    private String pseudo;

    @Column(name = "password")
    private String password;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "phone")
    private String phone;

    @Column(name = "birthday")
    private Timestamp birthday;

    @Column(name = "sign_up")
    private Timestamp sign_up;

    @Column(name = "push_notification")
    private boolean push_notification;

    @Column(name = "active_localisation")
    private boolean active_localisation;

    @Column(name = "display_real_name")
    private boolean display_real_name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_id_gender")
    private UserGenderEntity gender;
}
