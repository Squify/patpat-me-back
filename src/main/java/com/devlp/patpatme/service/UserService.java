package com.devlp.patpatme.service;

import com.devlp.patpatme.dto.user.CreateAccountDto;

import java.text.ParseException;

public interface UserService {

    void createUser(CreateAccountDto createAccountDto);
}
