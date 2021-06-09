package com.cc.cca3.controllers;

import com.cc.cca3.dtos.AccountDto;
import com.cc.cca3.models.UserEntity;
import com.cc.cca3.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@Validated
public class UserController {

    private final UserService userService;

    @PostMapping("/saveuserinfo")
    public ResponseEntity login(@RequestBody AccountDto accountDto) {
        AccountDto retrunedUser = userService.saveUserInfo(accountDto);
        return ResponseEntity.ok(retrunedUser);
    }

    @GetMapping("/getuserinfo")
    public ResponseEntity verifyStatus(@RequestParam(value = "id") Long id) {
        UserEntity retrunedUser = userService.findUserInfoByEmail(id);
        return ResponseEntity.ok(retrunedUser);
    }
}
