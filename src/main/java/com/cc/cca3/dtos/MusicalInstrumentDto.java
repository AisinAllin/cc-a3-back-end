package com.cc.cca3.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MusicalInstrumentDto {

    private Long musicId;

    private Long userId;

    private String type;

    private String name;

    private Long num_left;

    private Float price;

    private Long count;

    private String description;

}
