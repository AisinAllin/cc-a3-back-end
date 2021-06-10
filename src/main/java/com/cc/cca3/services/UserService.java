package com.cc.cca3.services;

import com.cc.cca3.dtos.AccountDto;
import com.cc.cca3.exceptions.UserNotFoundException;
import com.cc.cca3.models.UserEntity;
import com.cc.cca3.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public AccountDto saveUserInfo(AccountDto accountDto) {
        UserEntity returnedUser = userRepository.save(mapDtoToEntity(accountDto));

        return mapEntityToDto(returnedUser);
    }

    public Long findUserInfoByEmail(String uuid) {
        UserEntity returnedUser = userRepository.findByUuid(uuid).orElseThrow(
                () -> new UserNotFoundException("can not find user by id: " + uuid)
        );
        return returnedUser.getId();
    }

    public AccountDto fetchuserinfo(Long id) {
        UserEntity returnedUser = userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException("can not find user by id: " + id)
        );
        return mapEntityToDto(returnedUser);
    }

    @Transactional
    public void updateUserInfo(AccountDto accountDto) {
       userRepository.updateCompanyProfileById(accountDto.getId(), accountDto.getName(),accountDto.getAddress(), accountDto.getPhone());
    }

    private UserEntity mapDtoToEntity(AccountDto accountDto) {
        return UserEntity.builder()
                .uuid(accountDto.getUuid())
                .email(accountDto.getEmail())
                .name(accountDto.getName())
                .phone(accountDto.getPhone())
                .address(accountDto.getAddress())
                .build();
    }

    private AccountDto mapEntityToDto(UserEntity userEntity) {
        return AccountDto.builder()
                .id(userEntity.getId())
                .uuid(userEntity.getUuid())
                .name(userEntity.getName())
                .email(userEntity.getEmail())
                .phone(userEntity.getPhone())
                .address(userEntity.getAddress())
                .build();
    }

}
