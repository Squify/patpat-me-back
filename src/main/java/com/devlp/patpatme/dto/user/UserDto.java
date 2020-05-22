package com.devlp.patpatme.dto.user;

import com.devlp.patpatme.entity.UserGenderEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)

public class UserDto {
    private Integer id;
    private String email;
    private String pseudo;
    private String firstname;
    private String lastname;
    private String phone;
    private String birthday;
    private boolean push_notification;
    private boolean active_localisation;
    private boolean display_real_name;
    private UserGenderEntity gender;
}
