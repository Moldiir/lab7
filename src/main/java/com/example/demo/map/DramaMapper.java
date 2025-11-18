package com.example.demo.map;

import com.example.demo.dto.DramaDto;
import com.example.demo.entity.Drama;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring",
        uses = {ActorMapper.class, AwardMapper.class})
public interface DramaMapper {

    DramaDto toDto(Drama drama);

    Drama toEntity(DramaDto dto);

    List<DramaDto> toDtoList(List<Drama> dramas);
}
