package com.devlp.patpatme.service.implementation;

import com.devlp.patpatme.dto.animal.CreateAnimalDto;
import com.devlp.patpatme.entity.*;
import com.devlp.patpatme.repository.AnimalGenderRepository;
import com.devlp.patpatme.repository.AnimalRepository;
import com.devlp.patpatme.repository.AnimalTypeRepository;
import com.devlp.patpatme.service.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.time.ZonedDateTime;

public class AnimalServiceImpl implements AnimalService {

    @Autowired
    private AnimalRepository animalRepository;

    @Autowired
    private AnimalGenderRepository animalGenderRepository;

    @Autowired
    private AnimalTypeRepository animalTypeRepository;

    @Override
    @Transactional
    public void createAnimal(CreateAnimalDto createAnimalDto) {

        AnimalEntity newAnimal = new AnimalEntity();

        Timestamp signup = new Timestamp(System.currentTimeMillis());

        newAnimal.setName(createAnimalDto.getName());

        if (!createAnimalDto.getBirthday().isEmpty()) {
            ZonedDateTime birthdayZonedDateTime = ZonedDateTime.parse(createAnimalDto.getBirthday());
            Timestamp birthday = Timestamp.from(birthdayZonedDateTime.toInstant());
            newAnimal.setBirthday(birthday);
        }

        if (!createAnimalDto.getFk_id_gender().isEmpty()) {
            AnimalGenderEntity gender = animalGenderRepository.findOneByName(createAnimalDto.getFk_id_gender());
            if (gender != null)
                newAnimal.setGender(gender.getId());
        }

        if (!createAnimalDto.getFk_id_type().isEmpty()) {
            AnimalTypeEntity type = animalTypeRepository.findOneByName(createAnimalDto.getFk_id_type());
            if (type != null)
                newAnimal.setType(type.getId());
        }

        animalRepository.save(newAnimal);
    }
}

