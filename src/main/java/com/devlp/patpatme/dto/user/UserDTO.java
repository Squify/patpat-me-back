package com.devlp.patpatme.dto.user;

import com.devlp.patpatme.entity.LanguageEntity;
import com.devlp.patpatme.entity.UserEntity;
import com.devlp.patpatme.entity.UserGenderEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)

public class UserDTO {

    private Integer id;
    private String email;
    private String pseudo;
    private String profile_pic_path;
    private String firstname;
    private String lastname;
    private String phone;
    private String birthday;
    private boolean display_email;
    private boolean display_phone;
    private boolean display_real_name;
    private UserGenderEntity gender;
    private LanguageEntity language;
    private List<UserEntity> friends = new ArrayList<>();
}
