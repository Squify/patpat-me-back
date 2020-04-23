package com.devlp.patpatme.controller;

import com.devlp.patpatme.dto.animal.*;
import com.devlp.patpatme.service.AnimalService;
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

    // @ApiOperation(value = "Créer un nouvel animal dans la base de données")
    @PostMapping(value = "/api/animal/create")
    public ResponseEntity createAnimal(@RequestBody CreateAnimalDto createAnimalDto) {

        try {
            animalService.createAnimal(createAnimalDto);
            return new ResponseEntity(HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/api/animal/genders")
    public List<AnimalGenderDto> getGender() {
        return animalService.getAllGender();
    }

    @GetMapping(value = "/api/animal/types")
    public List<AnimalTypeDto> getType() {
        return animalService.getAllType();
    }
    @GetMapping(value = "/api/animal/tempers")
    public List<AnimalTemperDto> getTemper() {
        return animalService.getAllTemper();
    }

    @GetMapping(value = "/api/animal/races")
    public List<RaceDto> getRace() {
        return animalService.getAllRace();
    }
}
