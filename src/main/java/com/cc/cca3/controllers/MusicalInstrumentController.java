package com.cc.cca3.controllers;

import com.cc.cca3.dtos.AccountDto;
import com.cc.cca3.dtos.MusicalInstrumentDto;
import com.cc.cca3.models.MusicalInstrument;
import com.cc.cca3.models.UserEntity;
import com.cc.cca3.services.MusicalInstrumentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@Validated
public class MusicalInstrumentController {

    private final MusicalInstrumentService musicalInstrumentService;

    @GetMapping("/getmusicalinstrument")
    public ResponseEntity verifyStatus() {
        List<MusicalInstrument> retrunedMI = musicalInstrumentService.test();
        return ResponseEntity.ok(retrunedMI);
    }

    @PostMapping("/postmusicalinstrument")
    public ResponseEntity login(@RequestBody MusicalInstrumentDto musicalInstrumentDto) {
        MusicalInstrumentDto retrunedMI = musicalInstrumentService.saveMusicInst(musicalInstrumentDto);
        return ResponseEntity.ok(retrunedMI);
    }
}
