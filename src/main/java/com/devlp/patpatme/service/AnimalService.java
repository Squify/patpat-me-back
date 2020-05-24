package com.devlp.patpatme.service;

import com.devlp.patpatme.dto.animal.*;
import com.devlp.patpatme.entity.AnimalEntity;
import com.devlp.patpatme.entity.UserEntity;

public interface AnimalService {
    void createAnimal(UserEntity user, CreateAnimalDto createAnimalDto);
    void updateAnimal(UserEntity user, UpdateAnimalDto updateAnimalDto);
    AnimalDto getAnimalById(Integer animalId);
    AnimalEntity loadAnimalById(Integer id);
}
