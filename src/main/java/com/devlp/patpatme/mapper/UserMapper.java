package com.devlp.patpatme.mapper;

import com.devlp.patpatme.dto.user.AccountCreateDTO;
import com.devlp.patpatme.dto.user.UserDTO;
import com.devlp.patpatme.entity.UserEntity;
import com.devlp.patpatme.mapper.util.ModelMapperUtil;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {

    public static UserDTO toDTO(UserEntity user) {
        return ModelMapperUtil.createModelMapper(UserEntity.class, UserDTO.class, user);
    }

    public static List<UserDTO> toDTO(Collection<UserEntity> assets) {
        return assets.stream()
                .map(UserMapper::toDTO)
                .collect(Collectors.toList());
    }

    public static UserEntity toEntity(UserDTO dto) {
        return ModelMapperUtil.createModelMapper(UserDTO.class, UserEntity.class, dto);
    }

    public static UserEntity toEntity(AccountCreateDTO dto) {

        UserEntity user = ModelMapperUtil.createModelMapper(AccountCreateDTO.class, UserEntity.class, dto, UserEntity::setBirthday);

        if (!dto.getBirthday().isEmpty()) {
            ZonedDateTime birthdayZonedDateTime = ZonedDateTime.parse(dto.getBirthday());
            user.setBirthday(Timestamp.from(birthdayZonedDateTime.toInstant()));
        }

        user.setSign_up(Timestamp.from(Instant.now()));

        return user;
    }

}
