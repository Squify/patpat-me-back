package com.devlp.patpatme.controller;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class AnimalController {

    //TODO
    /*
    @Autowired
    private AnimalService animalService;

    @Autowired
    private AnimalGenderRepository animalGenderRepository;

    @ApiOperation(value = "Créer un nouvel animal dans la base de données")
    @PostMapping(value = "/api/animal/create")
    public ResponseEntity createAnimal(@RequestBody CreateAnimalDto createAnimalDto) {

        // check the inputs
        if (!UserUtil.checkCreatePersonInputsAreValid(createAnimalDto))
            return new ResponseEntity(HttpStatus.BAD_REQUEST);

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
    */


}
