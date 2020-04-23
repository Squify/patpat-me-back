package com.devlp.patpatme.service;

import com.devlp.patpatme.dto.animal.*;
import com.devlp.patpatme.repository.AnimalTemperRepository;

import java.util.List;

public interface AnimalService {
    void createAnimal(CreateAnimalDto createAnimalDto);
    List<AnimalTypeDto> getAllType();
    List<AnimalGenderDto> getAllGender();
    List<RaceDto> getAllRace();
    List<AnimalTemperDto> getAllTemper();
}
