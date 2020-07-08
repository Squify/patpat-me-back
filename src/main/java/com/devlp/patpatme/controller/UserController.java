package com.devlp.patpatme.controller;

import com.devlp.patpatme.dto.user.*;
import com.devlp.patpatme.entity.AnimalEntity;
import com.devlp.patpatme.entity.LanguageEntity;
import com.devlp.patpatme.entity.UserEntity;
import com.devlp.patpatme.entity.UserGenderEntity;
import com.devlp.patpatme.exception.UserNotFoundException;
import com.devlp.patpatme.mapper.UserMapper;
import com.devlp.patpatme.repository.AnimalRepository;
import com.devlp.patpatme.repository.LanguageRepository;
import com.devlp.patpatme.repository.UserGenderRepository;
import com.devlp.patpatme.security.CurrentUser;
import com.devlp.patpatme.service.UserService;
import com.devlp.patpatme.util.UserUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserGenderRepository userGenderRepository;

    @Autowired
    private LanguageRepository languageRepository;

    @Autowired
    private AnimalRepository animalRepository;

    @ApiOperation(value = "Créer un nouveau compte dans la base de données")
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
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Mets à jour le compte de l'utilisateur connecté dans la base de données")
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
            return new ResponseEntity(HttpStatus.OK);
        } catch (Throwable e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Récupère tous les genre possible pour un utilisateur")
    @GetMapping(value = "/api/genders")
    public List<UserGenderEntity> getGender() {
        return userGenderRepository.findAll();
    }

    @ApiOperation(value = "Récupère toutes les langues")
    @GetMapping(value = "/api/languages")
    public List<LanguageEntity> getLanguage() {
        return languageRepository.findAll();
    }

    @ApiOperation(value = "Renvoie un succès si la connexion réussie")
    @PostMapping(value = "/api/auth/login/success")
    public UserDTO loginSuccess() throws UserNotFoundException {

        CurrentUser user = (CurrentUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return getUserDtoFromCurrentUser(user);
    }

    @ApiOperation(value = "Renvoie une erreur si la connexion échoue")
    @RequestMapping("/api/generic/login/failure")
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public void failure() {
        // Controller used solely to send back an UNAUTHORIZED response
    }

    @ApiOperation(value = "Renvoie l'utilisateur connecté")
    @GetMapping(value = "/api/auth/user")
    public Object getUser(CurrentUser user) throws UserNotFoundException {

        return getUserDtoFromCurrentUser(user);
    }

    private UserDTO getUserDtoFromCurrentUser(CurrentUser user) throws UserNotFoundException {

        UserEntity userEntity = userService.loadUserById(user.getId());
        return UserMapper.toDTO(userEntity);
    }

    @ApiOperation(value = "Récupère un utilisateur en fonction de son id")
    @GetMapping(value = "/api/user")
    public Object getUser(@RequestParam Integer userId) {

        try {

            UserEntity userEntity = userService.loadUserById(userId);
            List<AnimalEntity> animalEntities = animalRepository.findAllByOwnerId(userId);

            FriendDTO friendDTO = new FriendDTO();
            friendDTO.setUser(userEntity).setAnimals(animalEntities);

            return friendDTO;
        } catch (Throwable e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Récupère tous les amis d'un utilisateur")
    @GetMapping(value = "/api/user/friends")
    public Object getFriends(CurrentUser user) {

        try {

            UserEntity userEntity = userService.loadUserById(user.getId());
            List<MinimalistFriendDTO> friendList = new ArrayList<>();
            for (UserEntity friend : userEntity.getFriends()) {
                friendList.add(UserMapper.toMiniFriendDTO(userService.loadUserById(friend.getId())));
            }
            return friendList;
        } catch (Throwable e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
