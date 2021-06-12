package com.cc.cca3.services;

import com.amazonaws.services.s3.AmazonS3;
import com.cc.cca3.dtos.MusicalInstrumentDto;
import com.cc.cca3.models.MusicalInstrument;
import com.cc.cca3.models.UserEntity;
import com.cc.cca3.repositories.MusicalInstrumentRepository;
import com.cc.cca3.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class MusicalInstrumentService {

    private static final String REGION = "ap-southeast-2";
    private final MusicalInstrumentRepository musicalInstrumentRepository;
    private final UserRepository userRepository;
    private final AmazonS3 amazonS3;

    public URL getImgUrlLinkFromS3(String id) {
        String imgName = id;
        return amazonS3.getUrl("cc-a3-img", imgName);
    }

    public List<MusicalInstrumentDto> test() {
        List<MusicalInstrument> returnedMI = musicalInstrumentRepository.getAllAndOrderByCount();
        return mapEntityToDot(returnedMI);
    }

    public List<MusicalInstrumentDto> getMusicInsByType(String type) {
        List<MusicalInstrument> returnedMI = musicalInstrumentRepository.findByType(type);
        return mapEntityToDot(returnedMI);
    }

    private List<MusicalInstrumentDto> mapEntityToDot(List<MusicalInstrument> musicLists) {


        List<MusicalInstrumentDto> result = musicLists.stream().map(temp -> {
            MusicalInstrumentDto musicGetDto = MusicalInstrumentDto.builder()
                    .musicId(temp.getId())
                    .userId(temp.getUserEntity().getId())
                    .type(temp.getType())
                    .name(temp.getName())
                    .num_left(temp.getNumLeft())
                    .price(temp.getPrice())
                    .added(temp.getAdded())
                    .count(temp.getCount())
                    .description(temp.getDescription())
                    .build();
            return musicGetDto;
        }).collect(Collectors.toList());
        return result;
    }

    public MusicalInstrumentDto saveMusicInst(MusicalInstrumentDto musicalInstrumentDto) {
        UserEntity user = userRepository.findById(musicalInstrumentDto.getUserId()).get();
        MusicalInstrument returnMI = mapDtoToEntity(user, musicalInstrumentDto);
        MusicalInstrument savedMI = musicalInstrumentRepository.save(returnMI);
        return mapEntityToDto(savedMI);
    }

    private MusicalInstrument mapDtoToEntity(UserEntity user, MusicalInstrumentDto musicalInstrumentDto) {
        return MusicalInstrument.builder()
                .userEntity(user)
                .type(musicalInstrumentDto.getType())
                .name(musicalInstrumentDto.getName())
                .numLeft(musicalInstrumentDto.getNum_left())
                .price(musicalInstrumentDto.getPrice())
                .count(musicalInstrumentDto.getCount())
                .description(musicalInstrumentDto.getDescription())
                .build();
    }

    private MusicalInstrumentDto mapEntityToDto(MusicalInstrument musicalInstrument) {
        return MusicalInstrumentDto.builder()
                .musicId(musicalInstrument.getId())
                .userId(musicalInstrument.getUserEntity().getId())
                .type(musicalInstrument.getType())
                .name(musicalInstrument.getName())
                .num_left(musicalInstrument.getNumLeft())
                .price(musicalInstrument.getPrice())
                .count(musicalInstrument.getCount())
                .description(musicalInstrument.getDescription())
                .build();
    }

    @Transactional
    public void updateCartStatus(Long musicId) {
        musicalInstrumentRepository.updateAddedById(musicId);
    }

    @Transactional
    public void updateCartStatusToFalse(Long musicId) {
        musicalInstrumentRepository.updateAddedByIdToFalse(musicId);

    }
}
