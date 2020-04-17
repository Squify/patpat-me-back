package com.devlp.patpatme.controller;



import com.devlp.patpatme.service.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import com.devlp.patpatme.dto.animal.CreateAnimalDto;
import com.devlp.patpatme.entity.AnimalGenderEntity;
import com.devlp.patpatme.entity.AnimalTypeEntity;
import com.devlp.patpatme.repository.AnimalGenderRepository;
import com.devlp.patpatme.repository.AnimalTypeRepository;



@RestController
public class AnimalController {

    @Autowired
    private AnimalService animalService;

    @Autowired
    private AnimalGenderRepository animalGenderRepository;

    @Autowired
    private AnimalTypeRepository  animalTypeRepository;

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
    public List<AnimalGenderEntity> getGender() {
        return animalGenderRepository.findAll();
    }

    @GetMapping(value = "/api/animal/types")
    public List<AnimalTypeEntity> getType() {
        return animalTypeRepository.findAll();
    }

}
