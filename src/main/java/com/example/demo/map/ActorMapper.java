package com.example.demo.map;

import com.example.demo.dto.ActorDto;
import com.example.demo.dto.ActorShortDto;
import com.example.demo.entity.Actor;
import org.mapstruct.Mapper;


import java.util.List;

@Mapper(componentModel = "spring")
public interface ActorMapper {

    ActorShortDto toShortDto(Actor actor);

    ActorDto toDto(Actor actor);
    Actor toEntity(ActorDto dto);

    List<ActorShortDto> toShortList(List<Actor> actors);
    List<ActorDto> toDtoList(List<Actor> actors);
}
