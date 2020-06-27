package com.devlp.patpatme.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class AccountCreateDTO {

    private String email;
    private String password;
    private String pseudo;
    private String profile_pic_path;
    private String firstname;
    private String lastname;
    private String phone;
    private String birthday;
    private boolean display_real_name;
    private boolean display_email;
    private boolean display_phone;
    private String gender;
}
