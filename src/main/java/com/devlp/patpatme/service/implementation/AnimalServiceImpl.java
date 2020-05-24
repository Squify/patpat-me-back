package com.devlp.patpatme.service.implementation;

import com.devlp.patpatme.dto.animal.AnimalDto;
import com.devlp.patpatme.dto.animal.CreateAnimalDTO;
import com.devlp.patpatme.dto.animal.UpdateAnimalDto;
import com.devlp.patpatme.entity.AnimalEntity;
import com.devlp.patpatme.entity.TemperEntity;
import com.devlp.patpatme.entity.UserEntity;
import com.devlp.patpatme.mapper.AnimalMapper;
import com.devlp.patpatme.repository.*;
import com.devlp.patpatme.service.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.time.ZonedDateTime;
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
    public void createAnimal(UserEntity user, CreateAnimalDTO createAnimalDto) {

        AnimalEntity animal = AnimalMapper.toEntity(createAnimalDto);
        animal.setOwner(user);

        if (createAnimalDto.getGender() != null) {
            animal.setGender(animalGenderRepository.findOneByName(createAnimalDto.getGender()));
        }

        if (createAnimalDto.getType() != null) {
            animal.setType(animalTypeRepository.findOneByName(createAnimalDto.getType()));
        }

        if (createAnimalDto.getBreed() != null) {
            animal.setBreed(breedRepository.findOneByName(createAnimalDto.getBreed()));
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
    public void updateAnimal(UpdateAnimalDto updateAnimalDto) {

        AnimalEntity updateAnimalEntity = animalRepository.findOneById(updateAnimalDto.getId());

        if (!updateAnimalDto.getBirthday().isEmpty()) {
            ZonedDateTime date = ZonedDateTime.parse(updateAnimalDto.getBirthday());
            updateAnimalEntity.setBirthday(Timestamp.from(date.toInstant()));
        } else
            updateAnimalEntity.setBirthday(null);

        if (!updateAnimalDto.getGender().isEmpty()) {
            updateAnimalEntity.setGender(animalGenderRepository.findOneByName(updateAnimalDto.getGender()));
        }

        if (!updateAnimalDto.getType().isEmpty()) {
            updateAnimalEntity.setType(animalTypeRepository.findOneByName(updateAnimalDto.getType()));
        }
        if (!updateAnimalDto.getBreed().isEmpty()) {
            updateAnimalEntity.setBreed(breedRepository.findOneByName(updateAnimalDto.getBreed()));
        }

        List<TemperEntity> temperEntities = new ArrayList<>();
        updateAnimalDto.getTempers().forEach(temper -> {
            temperEntities.add(temperRepository.findOneByName(temper));
        });
        updateAnimalEntity.setTempers(temperEntities);

        animalRepository.save(updateAnimalEntity);
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

