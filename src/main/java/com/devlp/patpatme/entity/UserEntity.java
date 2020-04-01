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
    private Integer id;

    @Column(name = "email")
    private String email;

    @Column(name = "pseudo")
    private String pseudo;

    @Column(name = "password")
    private String password;

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

    @Column(name = "push_notification")
    private boolean push_notification;

    @Column(name = "active_localisation")
    private boolean active_localisation;

    @Column(name = "display_real_name")
    private boolean display_real_name;

    //    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "fk_id_gender")
    @Column(name = "fk_id_gender")
    private Integer gender;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String name) {
        this.firstname = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String surname) {
        this.lastname = surname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Timestamp getBirthday() {
        return birthday;
    }

    public void setBirthday(Timestamp birthday) {
        this.birthday = birthday;
    }

    public Timestamp getSign_up() {
        return sign_up;
    }

    public void setSign_up(Timestamp sign_up) {
        this.sign_up = sign_up;
    }

    public boolean isPush_notification() {
        return push_notification;
    }

    public void setPush_notification(boolean push_notification) {
        this.push_notification = push_notification;
    }

    public boolean isActive_localisation() {
        return active_localisation;
    }

    public void setActive_localisation(boolean active_localisation) {
        this.active_localisation = active_localisation;
    }

    public boolean isDisplay_real_name() {
        return display_real_name;
    }

    public void setDisplay_real_name(boolean display_real_name) {
        this.display_real_name = display_real_name;
    }

//    public UserGenderEntity getGender() {
//        return gender;
//    }
//
//    public void setGender(UserGenderEntity gender) {
//        this.gender = gender;
//    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }
}
