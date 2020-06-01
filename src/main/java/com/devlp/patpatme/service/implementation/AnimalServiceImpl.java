package com.devlp.patpatme.service.implementation;

import com.devlp.patpatme.dto.animal.AnimalDto;
import com.devlp.patpatme.dto.animal.AnimalCreateDTO;
import com.devlp.patpatme.dto.animal.AnimalEditDTO;
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
    public void createAnimal(UserEntity user, AnimalCreateDTO animalCreateDto) {

        AnimalEntity animal = AnimalMapper.toEntity(animalCreateDto);
        animal.setOwner(user);

        if (animalCreateDto.getGender() != null) {
            animal.setGender(animalGenderRepository.findOneByName(animalCreateDto.getGender()));
        }

        if (animalCreateDto.getType() != null) {
            animal.setType(animalTypeRepository.findOneByName(animalCreateDto.getType()));
        }

        if (animalCreateDto.getBreed() != null) {
            animal.setBreed(breedRepository.findOneByName(animalCreateDto.getBreed()));
        }

        List<TemperEntity> temperEntities = new ArrayList<>();
        animalCreateDto.getTempers().forEach(temper -> {
            temperEntities.add(temperRepository.findOneByName(temper));
        });
        animal.setTempers(temperEntities);

        animalRepository.save(animal);
    }

    @Override
    @Transactional
    public void updateAnimal(AnimalEditDTO animalEditDTO) {

        AnimalEntity updateAnimalEntity = animalRepository.findOneById(animalEditDTO.getId());

        if (!animalEditDTO.getBirthday().isEmpty()) {
            ZonedDateTime date = ZonedDateTime.parse(animalEditDTO.getBirthday());
            updateAnimalEntity.setBirthday(Timestamp.from(date.toInstant()));
        } else
            updateAnimalEntity.setBirthday(null);

        if (!animalEditDTO.getGender().isEmpty()) {
            updateAnimalEntity.setGender(animalGenderRepository.findOneByName(animalEditDTO.getGender()));
        }

        if (!animalEditDTO.getType().isEmpty()) {
            updateAnimalEntity.setType(animalTypeRepository.findOneByName(animalEditDTO.getType()));
        }
        if (!animalEditDTO.getBreed().isEmpty()) {
            updateAnimalEntity.setBreed(breedRepository.findOneByName(animalEditDTO.getBreed()));
        }

        List<TemperEntity> temperEntities = new ArrayList<>();
        animalEditDTO.getTempers().forEach(temper -> {
            temperEntities.add(temperRepository.findOneByName(temper));
        });
        updateAnimalEntity.setTempers(temperEntities);

        animalRepository.save(updateAnimalEntity);
    }

    @Override
    public AnimalEntity getAnimalById(Integer animalId) {

        return animalRepository.findOneById(animalId);
    }

    @Override
    public AnimalEntity loadAnimalById(Integer id) {
        return animalRepository.findOneById(id);
    }

    @Override
    public void deleteAnimal(Integer animalId){
        animalRepository.deleteById(animalId);
    }
}

