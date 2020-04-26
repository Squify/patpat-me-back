package com.devlp.patpatme.service;

import com.devlp.patpatme.dto.user.CreateAccountDto;
import com.devlp.patpatme.entity.UserEntity;
import com.devlp.patpatme.exception.UserNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService {

    void createUser(CreateAccountDto createAccountDto);

    boolean userExistsWithEmail(String email);

    boolean userExistsWithPseudo(String pseudo);

    UserEntity loadUserByEmail(String email) throws UserNotFoundException;

    UserDetails loadUserByUsername(String email) throws UsernameNotFoundException;

    UserEntity loadUserById(Integer id) throws UserNotFoundException;
}
