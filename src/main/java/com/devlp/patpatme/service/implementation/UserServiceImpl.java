package com.devlp.patpatme.service.implementation;

import com.devlp.patpatme.dto.user.CreateAccountDto;
import com.devlp.patpatme.entity.UserEntity;
import com.devlp.patpatme.entity.UserGenderEntity;
import com.devlp.patpatme.repository.UserGenderRepository;
import com.devlp.patpatme.repository.UserRepository;
import com.devlp.patpatme.service.UserService;
import com.devlp.patpatme.util.BCryptManagerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.time.ZonedDateTime;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserGenderRepository userGenderRepository;

    @Autowired
    private BCryptManagerUtil bCryptManagerUtil;

    @Override
    @Transactional
    public void createUser(CreateAccountDto createAccountDto) {

        UserEntity newUser = new UserEntity();

        Timestamp signup = new Timestamp(System.currentTimeMillis());

        newUser.setEmail(createAccountDto.getMail());
        newUser.setPseudo(createAccountDto.getPseudo());
        newUser.setFirstname(createAccountDto.getFirstname());
        newUser.setLastname(createAccountDto.getLastname());
        newUser.setPhone(createAccountDto.getPhone());
        newUser.setPush_notification(createAccountDto.isPush_notification());
        newUser.setActive_localisation(createAccountDto.isActive_localisation());
        newUser.setDisplay_real_name(createAccountDto.isDisplay_real_name());
        newUser.setSign_up(signup);

        String password = bCryptManagerUtil.getPasswordEncoder().encode(createAccountDto.getPassword());
        newUser.setPassword(password);

        if (!createAccountDto.getBirthday().isEmpty()) {
            ZonedDateTime birthdayZonedDateTime = ZonedDateTime.parse(createAccountDto.getBirthday());
            Timestamp birthday = Timestamp.from(birthdayZonedDateTime.toInstant());
            newUser.setBirthday(birthday);
        }

        if (!createAccountDto.getFk_id_gender().isEmpty()) {
            UserGenderEntity gender = userGenderRepository.findOneByName(createAccountDto.getFk_id_gender());
            if (gender != null)
                newUser.setGender(gender.getId());
        }

        userRepository.save(newUser);
    }

    @Override
    public boolean userExistsWithMail(String mail) {
        return userRepository.existsUserEntityByEmailIgnoreCase(mail);
    }

    @Override
    public boolean userExistsWithPseudo(String pseudo) {
        return userRepository.existsUserEntityByPseudoIgnoreCase(pseudo);
    }

    @Override
    public UserEntity getUserById(int id) {
        return UserRepository.getUserById(id);
    }

}
