package com.devlp.patpatme.service;

import com.devlp.patpatme.dto.user.AccountCreateDTO;
import com.devlp.patpatme.dto.user.AccountEditDTO;
import com.devlp.patpatme.entity.UserEntity;
import com.devlp.patpatme.exception.UserNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService extends UserDetailsService {

    void createUser(AccountCreateDTO accountCreateDto);

    void editUser(UserEntity user, AccountEditDTO accountEditDTO);

    boolean userExistsWithEmail(String email);

    boolean userExistsWithPseudo(String pseudo);

    UserEntity loadUserByEmail(String email) throws UserNotFoundException;

    UserDetails loadUserByUsername(String email) throws UsernameNotFoundException;

    UserEntity loadUserById(Integer id) throws UserNotFoundException;
}
