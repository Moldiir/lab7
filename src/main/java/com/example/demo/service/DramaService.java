package com.example.demo.service;

import com.example.demo.dto.DramaDto;

import java.util.List;

public interface DramaService {

    List<DramaDto> getDramas();

    DramaDto getDrama(Long id);

    DramaDto addDrama(DramaDto dramaDto);

    DramaDto updateDrama(DramaDto dramaDto, Long id);

    boolean deleteDrama(Long id);
}
