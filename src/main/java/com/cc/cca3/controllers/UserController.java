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

    @PostMapping("/testpost")
    public ResponseEntity login(@RequestBody AccountDto accountDto) {
        UserEntity retrunedUser = userService.saveUserInfo(accountDto);
        return ResponseEntity.ok(retrunedUser);
    }

    @GetMapping("/testget")
    public ResponseEntity verifyStatus(@RequestParam(value = "email") String email) {
        UserEntity retrunedUser = userService.findUserInfoByEmail(email);
        return ResponseEntity.ok(retrunedUser);
    }
}
