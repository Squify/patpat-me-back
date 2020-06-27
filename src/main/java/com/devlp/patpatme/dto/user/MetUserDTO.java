package com.devlp.patpatme.dto.user;

import com.devlp.patpatme.entity.UserGenderEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class MetUserDTO {

    private Integer id;
    private String email;
    private String pseudo;
    private String profile_pic_path;
    private String firstname;
    private String lastname;
    private String phone;
    private boolean display_email;
    private boolean display_phone;
    private boolean display_real_name;
    private UserGenderEntity gender;
    private List<String> events = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MetUserDTO that = (MetUserDTO) o;
        return display_email == that.display_email &&
                display_phone == that.display_phone &&
                display_real_name == that.display_real_name &&
                Objects.equals(id, that.id) &&
                Objects.equals(email, that.email) &&
                Objects.equals(pseudo, that.pseudo) &&
                Objects.equals(profile_pic_path, that.profile_pic_path) &&
                Objects.equals(firstname, that.firstname) &&
                Objects.equals(lastname, that.lastname) &&
                Objects.equals(phone, that.phone) &&
                Objects.equals(gender, that.gender);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, pseudo, profile_pic_path, firstname, lastname, phone, display_email, display_phone, display_real_name, gender);
    }
}
