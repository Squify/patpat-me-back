package com.devlp.patpatme.controller;

import com.devlp.patpatme.dto.user.CreateAccountDto;
import com.devlp.patpatme.entity.UserEntity;
import com.devlp.patpatme.repository.UserRepository;
import com.devlp.patpatme.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    //    @ApiOperation(value = "Créer un nouveau compte dans la base de données")
    @PostMapping(value = "/api/user/create")
    public ResponseEntity createAccount(@RequestBody CreateAccountDto createAccountDto) {

        userService.createUser(createAccountDto);

        if (userRepository.findByEmail(createAccountDto.getMail()).isPresent()) {
            return new ResponseEntity(HttpStatus.CREATED);
        }

        else {
            return new ResponseEntity(HttpStatus.IM_USED);
        }

//        // Check the inputs
//        if (!PersonUtil.checkCreatePersonInputsAreValid(createAccountDto)) {
//            return new ResponseEntity(HttpStatus.BAD_REQUEST);
//        }
//
//        if (userService.personExistsWithMail(createAccountDto.getMail())) {
//            return new ResponseEntity(HttpStatus.EXPECTATION_FAILED);
//        }
//
//        try {
//            userService.createUser(createAccountDto);
//
//            return new ResponseEntity(HttpStatus.OK);
//
//        } catch (CesamRoleNotFoundException | LanguageNotFoundException e) {
//            // If cesam role doesn't exist in database
//            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
    }

}
