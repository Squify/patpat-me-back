package com.devlp.patpatme.service.implementation;

import com.devlp.patpatme.dto.user.CreateAccountDto;
import com.devlp.patpatme.entity.UserEntity;
import com.devlp.patpatme.entity.UserGenderEntity;
import com.devlp.patpatme.exception.UserNotFoundException;
import com.devlp.patpatme.repository.UserGenderRepository;
import com.devlp.patpatme.repository.UserRepository;
import com.devlp.patpatme.security.CurrentUser;
import com.devlp.patpatme.service.UserService;
import com.devlp.patpatme.util.BCryptManagerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.List;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

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

        newUser.setEmail(createAccountDto.getEmail());
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
    public boolean userExistsWithEmail(String email) {
        return userRepository.existsUserEntityByEmailIgnoreCase(email);
    }

    @Override
    public boolean userExistsWithPseudo(String pseudo) {
        return userRepository.existsUserEntityByPseudoIgnoreCase(pseudo);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        try {
            UserEntity userEntity = loadUserByEmail(email);
            return buildCurrentUserFromPersonEntity(userEntity);

        } catch (UserNotFoundException e) {
            throw new UsernameNotFoundException("User with email : " + email + " not found");
        }
    }

    @Override
    public UserEntity loadUserById(Integer id) throws UserNotFoundException {

        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User with id " + id + " not found in the database"));
    }

    @Override
    public UserEntity loadUserByEmail(String email) throws UserNotFoundException {

        return userRepository.findByEmailIgnoreCase(email).orElseThrow(() -> new UserNotFoundException("User with email " + email + " not found in the database"));
    }

    private User buildCurrentUserFromPersonEntity(UserEntity userEntity) {

        List<GrantedAuthority> authorities = buildAuthoritiesFromRoleEnum();
        return new CurrentUser(userEntity.getEmail(), userEntity.getPassword(), authorities, userEntity.getId());
    }

    private List<GrantedAuthority> buildAuthoritiesFromRoleEnum() {

        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("USER");
        return Collections.singletonList(grantedAuthority);
    }


}
