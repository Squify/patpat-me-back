package com.devlp.patpatme.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@Accessors(chain = true)
public class UserEntity {

    @Id
    @SequenceGenerator(name = "users_generator", sequenceName = "users_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_generator")
    @Column(name = "id")
    private Integer id;

    @Column(name = "email")
    private String email;

    @Column(name = "pseudo")
    private String pseudo;

    @Column(name = "password")
    private String password;

    @Column(name = "profile_pic_path")
    private String profile_pic_path;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "phone")
    private String phone;

    @Column(name = "birthday")
    private Timestamp birthday;

    @Column(name = "sign_up")
    private Timestamp sign_up;

    @Column(name = "display_real_name")
    private boolean display_real_name;

    @Column(name = "display_email")
    private boolean display_email;

    @Column(name = "display_phone")
    private boolean display_phone;

    @ManyToOne
    @JoinColumn(name = "fk_id_gender")
    private UserGenderEntity gender;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "follow_relation",
            joinColumns = @JoinColumn(name = "fk_id_user", referencedColumnName = "id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "fk_id_follower", referencedColumnName = "id", nullable = false)
    )
    private List<UserEntity> followers = new ArrayList<>();

}
