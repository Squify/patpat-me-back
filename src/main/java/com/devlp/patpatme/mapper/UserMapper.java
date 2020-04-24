package com.devlp.patpatme.mapper;

import com.devlp.patpatme.dto.user.UserDto;
import com.devlp.patpatme.entity.UserEntity;
import com.devlp.patpatme.mapper.util.ModelMapperUtil;

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

}
