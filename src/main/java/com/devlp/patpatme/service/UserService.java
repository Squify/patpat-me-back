package com.devlp.patpatme.service;

import com.devlp.patpatme.dto.user.CreateAccountDto;
import com.devlp.patpatme.dto.user.UserDto;
import com.devlp.patpatme.entity.UserEntity;

public interface UserService {

    void createUser(CreateAccountDto createAccountDto);
    boolean userExistsWithMail(String mail);
    boolean userExistsWithPseudo(String pseudo);
}
