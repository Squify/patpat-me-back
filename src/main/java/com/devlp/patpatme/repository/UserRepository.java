package com.devlp.patpatme.repository;

import com.devlp.patpatme.dto.user.UserDto;
import com.devlp.patpatme.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    boolean existsUserEntityByEmailIgnoreCase(String email);

    boolean existsUserEntityByPseudoIgnoreCase(String pseudo);

    UserEntity getUserById(int id);
}
