package com.devlp.patpatme.mapper;

import com.devlp.patpatme.dto.animal.AnimalDto;
import com.devlp.patpatme.dto.animal.CreateAnimalDto;
import com.devlp.patpatme.dto.animal.UpdateAnimalDto;
import com.devlp.patpatme.entity.AnimalEntity;
import com.devlp.patpatme.mapper.util.ModelMapperUtil;

import java.sql.Timestamp;
import java.time.ZonedDateTime;

public class AnimalMapper {

    public static AnimalDto toDTO(AnimalEntity animal) {
      return ModelMapperUtil.createModelMapper(AnimalEntity.class, AnimalDto.class, animal);
   }

   public static AnimalEntity toEntity(AnimalDto dto) {
       return ModelMapperUtil.createModelMapper(AnimalDto.class, AnimalEntity.class, dto);
    }

    public static AnimalEntity toEntity(CreateAnimalDto dto) {

        AnimalEntity animal = ModelMapperUtil.createModelMapper(CreateAnimalDTO.class, AnimalEntity.class, dto, AnimalEntity::setBirthday);

        if (!dto.getBirthday().isEmpty()) {
            ZonedDateTime date = ZonedDateTime.parse(dto.getBirthday());
            animal.setBirthday(Timestamp.from(date.toInstant()));
        }
        else
            animal.setBirthday(null);

        return animal;
    }

    public static AnimalEntity toEntity(UpdateAnimalDto dto) {

        AnimalEntity animal = ModelMapperUtil.createModelMapper(UpdateAnimalDto.class, AnimalEntity.class, dto, AnimalEntity::setBirthday);

        if (!dto.getBirthday().isEmpty()) {
            ZonedDateTime date = ZonedDateTime.parse(dto.getBirthday());
            animal.setBirthday(Timestamp.from(date.toInstant()));
        }
        else
            animal.setBirthday(null);

        return animal;
    }

}
