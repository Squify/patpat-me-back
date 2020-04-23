package com.devlp.patpatme.service;

import com.devlp.patpatme.dto.animal.*;
import com.devlp.patpatme.repository.AnimalTemperRepository;

import java.util.List;

public interface AnimalService {
    void createAnimal(CreateAnimalDto createAnimalDto);
    List<RaceDto> getAllRace();
    List<AnimalGenderDto> getAllGender();
    List<AnimalTemperDto> getAllTemper();
    List<AnimalTypeDto> getAllType();

}
