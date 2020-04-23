package com.devlp.patpatme.service.implementation;

import com.devlp.patpatme.dto.animal.*;
import com.devlp.patpatme.entity.*;
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
    private RaceRepository raceRepository;

    @Autowired
    private AnimalTemperRepository animalTemperRepository;

    @Override
    @Transactional
    public void createAnimal(CreateAnimalDto createAnimalDto) {
        System.out.println("test");
        AnimalEntity newAnimal = new AnimalEntity();

        Timestamp signup = new Timestamp(System.currentTimeMillis());

        newAnimal.setName(createAnimalDto.getName());
        System.out.println("test2");
        if (!createAnimalDto.getBirthday().isEmpty()) {
            ZonedDateTime birthdayZonedDateTime = ZonedDateTime.parse(createAnimalDto.getBirthday());
            Timestamp birthday = Timestamp.from(birthdayZonedDateTime.toInstant());
            newAnimal.setBirthday(birthday);
        }
        System.out.println("test3");
        if (!createAnimalDto.getFk_id_type().isEmpty()) {
            AnimalTypeEntity fk_id_type = animalTypeRepository.findOneByName(createAnimalDto.getFk_id_type());
            if (fk_id_type != null)
                newAnimal.setType(fk_id_type);
        }

        System.out.println("test4");
        if (!createAnimalDto.getFk_id_gender().isEmpty()) {
            AnimalGenderEntity fk_id_gender = animalGenderRepository.findOneByName(createAnimalDto.getFk_id_gender());
            if (fk_id_gender != null)
                newAnimal.setGender(fk_id_gender);
        }
        System.out.println("test5");
        if (!createAnimalDto.getFk_id_race().isEmpty()) {
            RaceEntity fk_id_race = raceRepository.findOneByName(createAnimalDto.getFk_id_race());
            if (fk_id_race != null)
                newAnimal.setRace(fk_id_race);
        }
        System.out.println("test6");
       /*
       NE FONCTIONNE PAS
        if (!createAnimalDto.getFk_id_temper().isEmpty()) {
            System.out.println("test61");
            AnimalTemperEntity temper = animalTemperRepository.findOneByName(createAnimalDto.getFk_id_temper());
            if (temper != null)
                System.out.println("test63");
             newAnimal.setTemper(temper);
            System.out.println("test62");
         }
        */
       //TODO faire le faire fk_id_owner
        newAnimal.setOwner(1);

        System.out.println("test7");
        animalRepository.save(newAnimal);
        System.out.println("test");
    }

    @Override
    public List<AnimalTypeDto> getAllType(){

        List<AnimalTypeDto> animalTypeDtos = new ArrayList<>();
        List<AnimalTypeEntity> types = animalTypeRepository.findAll();
        for ( AnimalTypeEntity type :types ) {
            AnimalTypeDto animalGenderDto = new AnimalTypeDto();
            animalGenderDto.setName(type.getName());
            animalTypeDtos.add(animalGenderDto);
        }
        return animalTypeDtos;
    }

    @Override
    public List<AnimalGenderDto> getAllGender() {

        List<AnimalGenderDto> animalGenderDtos = new ArrayList<>();
        List<AnimalGenderEntity> genders = animalGenderRepository.findAll();
        for ( AnimalGenderEntity gender :genders ) {
            AnimalGenderDto animalGenderDto = new AnimalGenderDto();
            animalGenderDto.setName(gender.getName());
            animalGenderDtos.add(animalGenderDto);
        }
        return animalGenderDtos;
    }

    @Override
    public List<RaceDto> getAllRace() {

        List<RaceDto> raceDtos = new ArrayList<>();
        List<RaceEntity> races = raceRepository.findAll();
        for (RaceEntity race : races) {
            RaceDto raceDto = new RaceDto();
            raceDto.setName(race.getName());
            raceDtos.add(raceDto);
        }
        return raceDtos;
    }


    @Override
    public List<AnimalTemperDto> getAllTemper(){

        List<AnimalTemperDto> animalTemperDtos = new ArrayList<>();
        List<AnimalTemperEntity> tempers = animalTemperRepository.findAll();
        for ( AnimalTemperEntity temper :tempers ) {
            AnimalTemperDto animalTemperDto = new AnimalTemperDto();
            animalTemperDto.setName(temper.getName());
            animalTemperDtos.add(animalTemperDto);
        }
        return animalTemperDtos;
    }



}

