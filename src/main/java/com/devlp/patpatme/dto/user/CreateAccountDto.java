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

    private String email;
    private String password;
    private String pseudo;
    private String firstname;
    private String lastname;
    private String phone;
    private String birthday;
    private boolean push_notification;
    private boolean active_localisation;
    private boolean display_real_name;
    private String gender;
}
