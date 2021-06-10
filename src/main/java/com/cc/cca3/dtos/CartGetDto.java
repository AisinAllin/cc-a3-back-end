package com.cc.cca3.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartGetDto {

    private Long cartId;

    private Long musicId;

    private Long userId;

    private String name;

    private Long numRequire;

    private Long numLeft;

    private Boolean added;

    private Float price;

}
