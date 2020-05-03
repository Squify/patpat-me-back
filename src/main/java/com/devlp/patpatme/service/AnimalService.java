package com.devlp.patpatme.service;

import com.devlp.patpatme.dto.animal.*;
import com.devlp.patpatme.entity.UserEntity;

import java.util.List;

public interface AnimalService {
    void createAnimal(UserEntity user, CreateAnimalDto createAnimalDto);
}
