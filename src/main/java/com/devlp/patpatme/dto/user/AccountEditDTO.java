package com.devlp.patpatme.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class AccountEditDTO {

    private String email;
    private String password;
    private String phone;
    private boolean push_notification;
    private boolean active_localisation;
    private boolean display_real_name;
    private String gender;
}
