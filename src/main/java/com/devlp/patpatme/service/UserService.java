package com.devlp.patpatme.service;

import com.devlp.patpatme.dto.user.CreateAccountDto;

public interface UserService {

    void createUser(CreateAccountDto createAccountDto);

    boolean userExistsWithMail(String mail);

    boolean userExistsWithPseudo(String pseudo);
}
