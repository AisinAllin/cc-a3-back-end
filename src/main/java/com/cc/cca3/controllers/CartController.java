package com.cc.cca3.controllers;

import com.cc.cca3.dtos.AccountDto;
import com.cc.cca3.dtos.CartGetDto;
import com.cc.cca3.dtos.CartPostDto;
import com.cc.cca3.models.UserEntity;
import com.cc.cca3.services.CartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@Validated
public class CartController {

    private final CartService cartService;

    @GetMapping("/getcartinfo")
    public ResponseEntity getCartInfo(@RequestParam(value = "id") Long id) {
        List<CartGetDto> returnedCartList = cartService.getCartListByUserId(id);
        return ResponseEntity.ok(returnedCartList);
    }

    @PostMapping("/postcartinfo")
    public ResponseEntity verifyStatus(@RequestBody CartPostDto cartPostDto) {
        CartGetDto returnedCart = cartService.saveInfoToCart(cartPostDto);
        return ResponseEntity.ok(returnedCart);
    }
}
