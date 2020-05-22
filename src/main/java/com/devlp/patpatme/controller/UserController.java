package com.devlp.patpatme.controller;

import com.devlp.patpatme.dto.user.AccountCreateDTO;
import com.devlp.patpatme.dto.user.AccountEditDTO;
import com.devlp.patpatme.dto.user.UserDTO;
import com.devlp.patpatme.entity.UserEntity;
import com.devlp.patpatme.entity.UserGenderEntity;
import com.devlp.patpatme.exception.UserNotFoundException;
import com.devlp.patpatme.mapper.UserMapper;
import com.devlp.patpatme.repository.UserGenderRepository;
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

    //    @ApiOperation(value = "Créer un nouveau compte dans la base de données")
    @PostMapping(value = "/api/user/create")
    public ResponseEntity createAccount(@RequestBody AccountCreateDTO accountCreateDto) {

        // check the inputs
        if (!UserUtil.checkCreatePersonInputsAreValid(accountCreateDto))
            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        // check if mail is already used
        if (userService.userExistsWithEmail(accountCreateDto.getEmail()))
            return new ResponseEntity(HttpStatus.EXPECTATION_FAILED);

        // check if pseudo is already used
        if (userService.userExistsWithPseudo(accountCreateDto.getPseudo()))
            return new ResponseEntity(HttpStatus.CONFLICT);

        try {

            userService.createUser(accountCreateDto);
            return new ResponseEntity(HttpStatus.CREATED);
        } catch (Throwable e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/api/user/update")
    public ResponseEntity editAccount(CurrentUser user, @RequestBody AccountEditDTO accountEditDto) throws UserNotFoundException {

        UserEntity userEntity = userService.loadUserById(user.getId());

        // check the inputs
        if (!UserUtil.checkEditPersonInputsAreValid(accountEditDto))
            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        // check if mail is already used
        if (userService.userExistsWithEmail(accountEditDto.getEmail()) && !userEntity.getEmail().equals(accountEditDto.getEmail()))
            return new ResponseEntity(HttpStatus.EXPECTATION_FAILED);

        try {
            userService.editUser(userEntity, accountEditDto);
            return new ResponseEntity(HttpStatus.CREATED);
        } catch (Throwable e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/api/genders")
    public List<UserGenderEntity> getGender() {
        return userGenderRepository.findAll();
    }

    @PostMapping(value = "/api/auth/login/success")
    public UserDTO loginSuccess() throws UserNotFoundException {

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

    private UserDTO getUserDtoFromCurrentUser(CurrentUser user) throws UserNotFoundException {

        UserEntity userEntity = userService.loadUserById(user.getId());
        return UserMapper.toDTO(userEntity);
    }

}
