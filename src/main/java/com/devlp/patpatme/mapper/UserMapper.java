package com.devlp.patpatme.mapper;

import com.devlp.patpatme.dto.user.CreateAccountDto;
import com.devlp.patpatme.dto.user.UserDto;
import com.devlp.patpatme.entity.UserEntity;
import com.devlp.patpatme.mapper.util.ModelMapperUtil;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {

    public static UserDto toDTO(UserEntity user) {
        return ModelMapperUtil.createModelMapper(UserEntity.class, UserDto.class, user);
    }

    public static List<UserDto> toDTO(Collection<UserEntity> assets) {
        return assets.stream()
                .map(UserMapper::toDTO)
                .collect(Collectors.toList());
    }

    public static UserEntity toEntity(UserDto dto) {
        return ModelMapperUtil.createModelMapper(UserDto.class, UserEntity.class, dto);
    }

    public static UserEntity toEntity(CreateAccountDto dto) {

        UserEntity user = ModelMapperUtil.createModelMapper(CreateAccountDto.class, UserEntity.class, dto, UserEntity::setBirthday);

        if (!dto.getBirthday().isEmpty()) {
            ZonedDateTime birthdayZonedDateTime = ZonedDateTime.parse(dto.getBirthday());
            user.setBirthday(Timestamp.from(birthdayZonedDateTime.toInstant()));
        }

        user.setSign_up(Timestamp.from(Instant.now()));

        return user;
    }

}
