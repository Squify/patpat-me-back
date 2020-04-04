package com.devlp.patpatme.controller;

import com.devlp.patpatme.dto.user.CreateAccountDto;
import com.devlp.patpatme.entity.UserEntity;
import com.devlp.patpatme.entity.UserGenderEntity;
import com.devlp.patpatme.repository.UserGenderRepository;
import com.devlp.patpatme.repository.UserRepository;
import com.devlp.patpatme.service.UserService;
import com.devlp.patpatme.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserGenderRepository userGenderRepository;

    //    @ApiOperation(value = "Créer un nouveau compte dans la base de données")
    @PostMapping(value = "/api/user/create")
    public ResponseEntity createAccount(@RequestBody CreateAccountDto createAccountDto) {

        // check the inputs
        if (!UserUtil.checkCreatePersonInputsAreValid(createAccountDto))
            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        // check if mail is already used
        if (userService.userExistsWithMail(createAccountDto.getMail()))
            return new ResponseEntity(HttpStatus.EXPECTATION_FAILED);

        // check if pseudo is already used
        if (userService.userExistsWithPseudo(createAccountDto.getPseudo()))
            return new ResponseEntity(HttpStatus.CONFLICT);

        try {

            userService.createUser(createAccountDto);
            return new ResponseEntity(HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/api/genders")
    public List<UserGenderEntity> getGender() {
        return userGenderRepository.findAll();
    }

    @GetMapping(value = "api/user/get")
    public UserEntity getUser() {
        UserEntity user = UserRepository.getUserById(1);
        return (user);
    }
}
