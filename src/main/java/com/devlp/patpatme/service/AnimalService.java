package com.devlp.patpatme.service;

import com.devlp.patpatme.dto.animal.*;
import com.devlp.patpatme.entity.UserEntity;

public interface AnimalService {
    void createAnimal(UserEntity user, CreateAnimalDTO createAnimalDto);
}
