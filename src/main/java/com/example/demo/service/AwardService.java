package com.example.demo.service;

import com.example.demo.dto.AwardDto;

import java.util.List;

public interface AwardService {

    List<AwardDto> getAwards();

    AwardDto getAward(Long id);

    AwardDto addAward(AwardDto awardDto);

    AwardDto updateAward(AwardDto awardDto, Long id);

    boolean deleteAward(Long id);
}
