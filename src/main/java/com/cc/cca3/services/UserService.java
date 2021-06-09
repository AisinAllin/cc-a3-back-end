package com.cc.cca3.services;

import com.cc.cca3.dtos.AccountDto;
import com.cc.cca3.exceptions.UserNotFoundException;
import com.cc.cca3.models.UserEntity;
import com.cc.cca3.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserEntity saveUserInfo(AccountDto accountDto) {
        UserEntity returnedUser = userRepository.save(mapDtoToEntity(accountDto));
        return returnedUser;
    }

    public UserEntity findUserInfoByEmail(String email) {
        UserEntity returnedUser = userRepository.findByEmail(email).orElseThrow(
                () -> new UserNotFoundException("can not find user by email: " + email)
        );
        return returnedUser;
    }

    private UserEntity mapDtoToEntity(AccountDto accountDto) {
        return UserEntity.builder()
                .email(accountDto.getEmail())
                .name(accountDto.getName())
                .phone("04811111111")
                .password(accountDto.getPassword())
                .status("status")
                .title(accountDto.getTitle())
                .build();
    }

}
