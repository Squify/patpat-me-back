package com.devlp.patpatme.controller;

import com.devlp.patpatme.dto.animal.AnimalCreateDTO;
import com.devlp.patpatme.dto.animal.AnimalEditDTO;
import com.devlp.patpatme.entity.*;
import com.devlp.patpatme.exception.UserNotFoundException;
import com.devlp.patpatme.repository.*;
import com.devlp.patpatme.security.CurrentUser;
import com.devlp.patpatme.service.AnimalService;
import com.devlp.patpatme.service.UserService;
import com.devlp.patpatme.util.AnimalUtil;
import io.swagger.annotations.ApiOperation;
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

    @ApiOperation(value = "Créer un nouvel animal dans la base de données")
    @PostMapping(value = "/api/animal/create")
    public ResponseEntity createAnimal(CurrentUser user, @RequestBody AnimalCreateDTO animalCreateDto) {

        // check the inputs
        if (!AnimalUtil.checkCreateAnimalInputsAreValid(animalCreateDto))
            return new ResponseEntity(HttpStatus.BAD_REQUEST); //400

        try {
            UserEntity userEntity = userService.loadUserById(user.getId());
            animalService.createAnimal(userEntity, animalCreateDto);
            return new ResponseEntity(HttpStatus.CREATED);
        } catch (Throwable e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Mets à jour un animal dans la base de données")
    @PostMapping(value = "/api/animal/update")
    public ResponseEntity updateAnimal(CurrentUser user, @RequestBody AnimalEditDTO animalEditDTO) {

        // check the inputs
        if (!AnimalUtil.checkEditAnimalInputsAreValid(animalEditDTO))
            return new ResponseEntity(HttpStatus.BAD_REQUEST); //400

        // check if connected user is owner
        if (!user.getId().equals(animalEditDTO.getOwner()))
            return new ResponseEntity(HttpStatus.EXPECTATION_FAILED); //417

        try {
            animalService.updateAnimal(animalEditDTO);
            return new ResponseEntity(HttpStatus.CREATED);
        } catch (Throwable e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Récupère tous les types d'animaux")
    @GetMapping(value = "/api/animal/types")
    public List<AnimalTypeEntity> getType() {
        return animalTypeRepository.findAll();
    }

    @ApiOperation(value = "Récupère tous les genre pour un animal")
    @GetMapping(value = "/api/animal/genders")
    public List<AnimalGenderEntity> getGender() {
        return animalGenderRepository.findAll();
    }

    @ApiOperation(value = "Récupère toutes les races disponibles")
    @GetMapping(value = "/api/animal/breeds")
    public List<BreedEntity> getBreed() {
        return breedRepository.findAll();
    }

    @ApiOperation(value = "Récupère tous les tempéraments disponibles")
    @GetMapping(value = "/api/animal/tempers")
    public List<TemperEntity> getTemper() {
        return temperRepository.findAll();
    }

    @ApiOperation(value = "Récupère tous les animaux d'un utilisateur")
    @GetMapping(value = "/api/animals")
    public List<AnimalEntity> getUserAnimals(CurrentUser user) throws UserNotFoundException {

        UserEntity userEntity = userService.loadUserById(user.getId());
        return animalRepository.findAllByOwnerId(userEntity.getId());
    }

    @ApiOperation(value = "Récupère un animal en fonction de son id")
    @GetMapping(value = "/api/animal")
    public Object getAnimal(@RequestParam Integer animalId) {

        try {
            return animalService.getAnimalById(animalId);
        } catch (Throwable e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Supprime un animal en fonction de son id")
    @DeleteMapping(value = "/api/animal/delete")
    public ResponseEntity deleteAnimal(@RequestParam Integer animalId, CurrentUser user) {

        AnimalEntity animal = animalRepository.findOneById(animalId);

        // check if animal exist
        if (animal == null)
            return new ResponseEntity(HttpStatus.BAD_REQUEST); //400

        // check if connected user is owner
        if (!user.getId().equals(animal.getOwner().getId()))
            return new ResponseEntity(HttpStatus.EXPECTATION_FAILED); //417

        try {
            animalService.deleteAnimal(animalId);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Throwable e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
