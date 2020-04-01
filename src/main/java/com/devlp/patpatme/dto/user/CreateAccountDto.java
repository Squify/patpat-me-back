package com.devlp.patpatme.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class CreateAccountDto {

    private String mail;
    private String password;
    private String pseudo;
    private String firstname;
    private String lastname;
    private String phone;
    private String birthday;
    private boolean push_notification;
    private boolean active_localisation;
    private boolean display_real_name;
    private String fk_id_gender;

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
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

    public String getFk_id_gender() {
        return fk_id_gender;
    }

    public void setFk_id_gender(String fk_id_gender) {
        this.fk_id_gender = fk_id_gender;
    }
}
