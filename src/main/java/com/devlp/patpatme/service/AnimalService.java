package com.devlp.patpatme.service;

import com.devlp.patpatme.dto.animal.AnimalDto;
import com.devlp.patpatme.dto.animal.CreateAnimalDTO;
import com.devlp.patpatme.dto.animal.UpdateAnimalDto;
import com.devlp.patpatme.entity.AnimalEntity;
import com.devlp.patpatme.entity.UserEntity;

public interface AnimalService {
    void createAnimal(UserEntity user, CreateAnimalDTO createAnimalDto);

    void updateAnimal(UpdateAnimalDto updateAnimalDto);

    AnimalDto getAnimalById(Integer animalId);

    AnimalEntity loadAnimalById(Integer id);
}
