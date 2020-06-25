package com.devlp.patpatme.service.implementation;

import com.devlp.patpatme.dto.user.AccountCreateDTO;
import com.devlp.patpatme.dto.user.AccountEditDTO;
import com.devlp.patpatme.entity.UserEntity;
import com.devlp.patpatme.entity.UserGenderEntity;
import com.devlp.patpatme.exception.UserNotFoundException;
import com.devlp.patpatme.mapper.UserMapper;
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
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;

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
    public void createUser(AccountCreateDTO accountCreateDto) {

        UserEntity user = UserMapper.toEntity(accountCreateDto);

        user.setPassword(bCryptManagerUtil.getPasswordEncoder().encode(accountCreateDto.getPassword()));

        if (!accountCreateDto.getGender().isEmpty()) {
            user.setGender(userGenderRepository.findOneByName(accountCreateDto.getGender()));
        }

        userRepository.save(user);
    }

    @Override
    public void editUser(UserEntity user, AccountEditDTO accountEditDTO) {

        if (!user.getEmail().equals(accountEditDTO.getEmail()))
            user.setEmail(accountEditDTO.getEmail());

        if (!user.getProfile_pic_path().equals(accountEditDTO.getProfile_pic_path()))
            user.setProfile_pic_path(accountEditDTO.getProfile_pic_path());

        if (!user.getPhone().equals(accountEditDTO.getPhone()))
            user.setPhone(accountEditDTO.getPhone());

        if (user.isDisplay_email() != accountEditDTO.isDisplay_email())
            user.setDisplay_email(accountEditDTO.isDisplay_email());

        if (user.isDisplay_phone() != accountEditDTO.isDisplay_phone())
            user.setDisplay_phone(accountEditDTO.isDisplay_phone());

        if (user.isDisplay_real_name() != accountEditDTO.isDisplay_real_name())
            user.setDisplay_real_name(accountEditDTO.isDisplay_real_name());

        if (!accountEditDTO.getPassword().isEmpty())
            user.setPassword(bCryptManagerUtil.getPasswordEncoder().encode(accountEditDTO.getPassword()));

        if (!accountEditDTO.getGender().isEmpty()) {
            UserGenderEntity userGender = userGenderRepository.findOneByName(accountEditDTO.getGender());
            if (user.getGender() != userGender)
                user.setGender(userGender);
        }

        userRepository.save(user);
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
