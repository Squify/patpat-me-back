package com.devlp.patpatme.controller;

import com.devlp.patpatme.dto.animal.CreateAnimalDTO;
import com.devlp.patpatme.entity.*;
import com.devlp.patpatme.repository.AnimalGenderRepository;
import com.devlp.patpatme.repository.TemperRepository;
import com.devlp.patpatme.repository.AnimalTypeRepository;
import com.devlp.patpatme.repository.BreedRepository;
import com.devlp.patpatme.security.CurrentUser;
import com.devlp.patpatme.service.AnimalService;
import com.devlp.patpatme.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AnimalController {

    @Autowired
    private AnimalService animalService;

    @Autowired
    private AnimalTypeRepository animalTypeRepository;

    @Autowired
    private AnimalGenderRepository animalGenderRepository;

    @Autowired
    private BreedRepository breedRepository;

    @Autowired
    private TemperRepository temperRepository;

    @Autowired
    private UserService userService;

    // @ApiOperation(value = "Créer un nouvel animal dans la base de données")
    @PostMapping(value = "/api/animal/create")
    public ResponseEntity createAnimal(CurrentUser user, @RequestBody CreateAnimalDTO createAnimalDto) {

        try {

            UserEntity userEntity = userService.loadUserById(user.getId());
            animalService.createAnimal(userEntity, createAnimalDto);
            return new ResponseEntity(HttpStatus.CREATED);
        } catch (Throwable e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/api/animal/types")
    public List<AnimalTypeEntity> getType() {
        return animalTypeRepository.findAll();
    }

    @GetMapping(value = "/api/animal/genders")
    public List<AnimalGenderEntity> getGender() {
        return animalGenderRepository.findAll();
    }

    @GetMapping(value = "/api/animal/breeds")
    public List<BreedEntity> getBreed() {
        return breedRepository.findAll();
    }

    @GetMapping(value = "/api/animal/tempers")
    public List<TemperEntity> getTemper() {
        return temperRepository.findAll();
    }
}
