package com.devlp.patpatme.security;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
public class CurrentUser extends User {

    private Integer id;

    public CurrentUser(String email, String password, List<GrantedAuthority> authorities, Integer id) {

        super(email, password, authorities);
        this.id = id;
    }
}
