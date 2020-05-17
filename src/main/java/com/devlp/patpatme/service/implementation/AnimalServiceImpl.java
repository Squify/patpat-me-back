package com.devlp.patpatme.service.implementation;

import com.devlp.patpatme.dto.animal.CreateAnimalDto;
import com.devlp.patpatme.entity.AnimalEntity;
import com.devlp.patpatme.entity.TemperEntity;
import com.devlp.patpatme.entity.UserEntity;
import com.devlp.patpatme.mapper.AnimalMapper;
import com.devlp.patpatme.repository.*;
import com.devlp.patpatme.service.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class AnimalServiceImpl implements AnimalService {

    @Autowired
    private AnimalRepository animalRepository;

    @Autowired
    private AnimalGenderRepository animalGenderRepository;

    @Autowired
    private AnimalTypeRepository animalTypeRepository;

    @Autowired
    private BreedRepository breedRepository;

    @Autowired
    private TemperRepository temperRepository;

    @Override
    @Transactional
    public void createAnimal(UserEntity user, CreateAnimalDto createAnimalDto) {

        AnimalEntity animal = AnimalMapper.toEntity(createAnimalDto);
        animal.setOwner(user);

        if (!createAnimalDto.getFk_id_gender().isEmpty()) {
            animal.setGender(animalGenderRepository.findOneByName(createAnimalDto.getFk_id_gender()));
        }

        if (!createAnimalDto.getFk_id_type().isEmpty()) {
            animal.setType(animalTypeRepository.findOneByName(createAnimalDto.getFk_id_type()));
        }

        if (!createAnimalDto.getFk_id_breed().isEmpty()) {
            animal.setBreed(breedRepository.findOneByName(createAnimalDto.getFk_id_breed()));
        }

        List<TemperEntity> temperEntities = new ArrayList<>();
        createAnimalDto.getTempers().forEach(temper -> {
            temperEntities.add(temperRepository.findOneByName(temper));
        });
        animal.setTempers(temperEntities);

        animalRepository.save(animal);
    }
}
