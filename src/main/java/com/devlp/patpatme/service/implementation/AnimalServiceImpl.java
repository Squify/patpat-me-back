package com.devlp.patpatme.service.implementation;

import com.devlp.patpatme.dto.animal.AnimalDto;
import com.devlp.patpatme.dto.animal.CreateAnimalDto;
import com.devlp.patpatme.dto.animal.UpdateAnimalDto;
import com.devlp.patpatme.entity.AnimalEntity;
import com.devlp.patpatme.entity.TemperEntity;
import com.devlp.patpatme.entity.UserEntity;
import com.devlp.patpatme.exception.UserNotFoundException;
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

    @Override
    @Transactional
    public void updateAnimal(UserEntity user, UpdateAnimalDto updateAnimalDto) {

        AnimalEntity animal = AnimalMapper.toEntity(updateAnimalDto);
        animal.setOwner(user);

        if (!updateAnimalDto.getFk_id_gender().isEmpty()) {
            animal.setGender(animalGenderRepository.findOneByName(updateAnimalDto.getFk_id_gender()));
        }

        if (!updateAnimalDto.getFk_id_type().isEmpty()) {
            animal.setType(animalTypeRepository.findOneByName(updateAnimalDto.getFk_id_type()));
        }

        if (!updateAnimalDto.getFk_id_breed().isEmpty()) {
            animal.setBreed(breedRepository.findOneByName(updateAnimalDto.getFk_id_breed()));
        }

        List<TemperEntity> temperEntities = new ArrayList<>();
        updateAnimalDto.getTempers().forEach(temper -> {
            temperEntities.add(temperRepository.findOneByName(temper));
        });
        animal.setTempers(temperEntities);

        animalRepository.save(animal);
    }

    @Override
    public AnimalDto getAnimalById(Integer animalId) {

        AnimalEntity animalEntity = animalRepository.findOneById(animalId);
        return AnimalMapper.toDTO(animalEntity);
        //        AnimalDto animalDto = new AnimalDto();
        //        return animalDto
        //                .setId(animalEntity.getId())
        //                .setName(animalEntity.getName())
        //                .setBirthday(animalEntity.getBirthday().toString())
        //                .setFk_id_breed(animalEntity.getBreed().getId().toString())
        //                .setFk_id_gender(animalEntity.getGender().getId().toString())
        //                .setFk_id_type(animalEntity.getType().getId().toString());
        //        //TODO liste des temp
    }

    @Override
    public AnimalEntity loadAnimalById(Integer id) {
        return animalRepository.findOneById(id);
    }

}

