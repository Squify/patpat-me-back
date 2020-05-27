package com.devlp.patpatme.service;

import com.devlp.patpatme.dto.animal.AnimalDto;
import com.devlp.patpatme.dto.animal.AnimalCreateDTO;
import com.devlp.patpatme.dto.animal.AnimalEditDTO;
import com.devlp.patpatme.entity.AnimalEntity;
import com.devlp.patpatme.entity.UserEntity;

public interface AnimalService {
    void createAnimal(UserEntity user, AnimalCreateDTO animalCreateDto);

    void updateAnimal(AnimalEditDTO animalEditDTO);

    AnimalEntity getAnimalById(Integer animalId);

    AnimalEntity loadAnimalById(Integer id);
}
