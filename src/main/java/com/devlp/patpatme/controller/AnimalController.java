package com.devlp.patpatme.controller;

import com.devlp.patpatme.dto.animal.CreateAnimalDTO;
import com.devlp.patpatme.dto.animal.UpdateAnimalDto;
import com.devlp.patpatme.entity.*;
import com.devlp.patpatme.exception.UserNotFoundException;
import com.devlp.patpatme.repository.*;
import com.devlp.patpatme.security.CurrentUser;
import com.devlp.patpatme.service.AnimalService;
import com.devlp.patpatme.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    private AnimalRepository animalRepository;

    @Autowired
    private UserService userService;

    // @ApiOperation(value = "Créer un nouvel animal dans la base de données")
    @PostMapping(value = "/api/animal/create")
    public ResponseEntity createAnimal(CurrentUser user, @RequestBody CreateAnimalDTO createAnimalDto) {

        //TODO: Faire les checks d'input

        try {
            UserEntity userEntity = userService.loadUserById(user.getId());
            animalService.createAnimal(userEntity, createAnimalDto);
            return new ResponseEntity(HttpStatus.CREATED);
        } catch (Throwable e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/api/animal/update")
    public ResponseEntity updateAnimal(CurrentUser user, @RequestBody UpdateAnimalDto updateAnimalDto) {

        //TODO: Faire les checks d'input
        //TODO: vérifier si l'user en cours est l'owner
        System.out.println(updateAnimalDto);

        try {
            animalService.updateAnimal(updateAnimalDto);
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

    @GetMapping(value = "/api/animals")
    public List<AnimalEntity> getUserAnimals(CurrentUser user) throws UserNotFoundException {

        UserEntity userEntity = userService.loadUserById(user.getId());
        return animalRepository.findAllByOwnerId(userEntity.getId());
    }

    @GetMapping(value = "/api/animal")
    public Object getAnimal(@RequestParam Integer animalId) {

        try {
            return animalService.getAnimalById(animalId);
        } catch (Throwable e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
