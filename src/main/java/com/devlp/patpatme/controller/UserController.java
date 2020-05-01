package com.devlp.patpatme.controller;

import com.devlp.patpatme.dto.user.CreateAccountDto;
import com.devlp.patpatme.dto.user.UserDto;
import com.devlp.patpatme.entity.UserEntity;
import com.devlp.patpatme.entity.UserGenderEntity;
import com.devlp.patpatme.exception.UserNotFoundException;
import com.devlp.patpatme.mapper.UserMapper;
import com.devlp.patpatme.repository.UserGenderRepository;
import com.devlp.patpatme.repository.UserRepository;
import com.devlp.patpatme.security.CurrentUser;
import com.devlp.patpatme.service.UserService;
import com.devlp.patpatme.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserGenderRepository userGenderRepository;

    @Autowired
    private UserRepository userRepository;

    //    @ApiOperation(value = "Créer un nouveau compte dans la base de données")
    @PostMapping(value = "/api/user/create")
    public ResponseEntity createAccount(@RequestBody CreateAccountDto createAccountDto) {

        // check the inputs
        if (!UserUtil.checkCreatePersonInputsAreValid(createAccountDto))
            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        // check if mail is already used
        if (userService.userExistsWithEmail(createAccountDto.getEmail()))
            return new ResponseEntity(HttpStatus.EXPECTATION_FAILED);

        // check if pseudo is already used
        if (userService.userExistsWithPseudo(createAccountDto.getPseudo()))
            return new ResponseEntity(HttpStatus.CONFLICT);

        try {

            userService.createUser(createAccountDto);
            return new ResponseEntity(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/api/genders")
    public List<UserGenderEntity> getGender() {
        return userGenderRepository.findAll();
    }

    @PostMapping(value = "/api/auth/login/success")
    public UserDto loginSuccess() throws UserNotFoundException {

        CurrentUser user = (CurrentUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return getUserDtoFromCurrentUser(user);
    }

    @RequestMapping("/api/generic/login/failure")
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public void failure() {
        // Controller used solely to send back an UNAUTHORIZED response
    }

    @GetMapping(value = "/api/auth/user")
    public Object getUser(CurrentUser user) throws UserNotFoundException {

        return getUserDtoFromCurrentUser(user);
    }

    private UserDto getUserDtoFromCurrentUser(CurrentUser user) throws UserNotFoundException {

        UserEntity userEntity = userService.loadUserById(user.getId());
        return UserMapper.toDTO(userEntity);
    }

}
