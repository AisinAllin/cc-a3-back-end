package com.cc.cca3.controllers;

import com.cc.cca3.dtos.MusicalInstrumentDto;
import com.cc.cca3.services.MusicalInstrumentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URL;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@Validated
public class MusicalInstrumentController {

    private final MusicalInstrumentService musicalInstrumentService;

    @GetMapping("/getmusicalinstrument")
    public ResponseEntity getPopMusicIns() {
        List<MusicalInstrumentDto> retrunedMI = musicalInstrumentService.test();
        return ResponseEntity.ok(retrunedMI);
    }

    @PostMapping("/postmusicalinstrument")
    public ResponseEntity login(@RequestBody MusicalInstrumentDto musicalInstrumentDto) {
        MusicalInstrumentDto retrunedMI = musicalInstrumentService.saveMusicInst(musicalInstrumentDto);
        return ResponseEntity.ok(retrunedMI);
    }

    @GetMapping("/getmusicinsbytype")
    public ResponseEntity getMusicInsByType(@RequestParam(value = "type") String type) {
        List<MusicalInstrumentDto> retrunedMI = musicalInstrumentService.getMusicInsByType(type);
        return ResponseEntity.ok(retrunedMI);
    }

    @PutMapping("/updateaddstatus")
    public ResponseEntity updateCartStatus(@RequestBody MusicalInstrumentDto musicalInstrumentDto) {
        musicalInstrumentService.updateCartStatus(musicalInstrumentDto.getMusicId());
        return ResponseEntity.ok("success");
    }

    @PutMapping("/updateaddstatusfalse")
    public ResponseEntity updateCartStatusToFalse(@RequestBody MusicalInstrumentDto musicalInstrumentDto) {
        musicalInstrumentService.updateCartStatusToFalse(musicalInstrumentDto.getMusicId());
        return ResponseEntity.ok("success");
    }

    @GetMapping("/s3imglink")
    public URL getArtistImgLink(@RequestParam(value = "musicId") String musicId) {
        return musicalInstrumentService.getImgUrlLinkFromS3(musicId);
    }
}
