package com.example.demo.map;

import com.example.demo.dto.AwardDto;
import com.example.demo.dto.AwardShortDto;
import com.example.demo.entity.Award;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AwardMapper {


    AwardDto toDto(Award award);
    Award toEntity(AwardDto dto);

    List<AwardDto> toDtoList(List<Award> awards);


    AwardShortDto toShortDto(Award award);
    List<AwardShortDto> toShortDtoList(List<Award> awards);
}
