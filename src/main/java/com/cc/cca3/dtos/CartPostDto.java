package com.cc.cca3.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartPostDto {

    private Long musicId;

    private Long userId;

    private Long numRequire;

}
