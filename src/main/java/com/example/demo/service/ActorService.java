package com.example.demo.service;

import com.example.demo.dto.ActorDto;

import java.util.List;

public interface ActorService {

    List<ActorDto> getActors();

    ActorDto getActor(Long id);

    ActorDto addActor(ActorDto actorDto);

    ActorDto updateActor(ActorDto actorDto, Long id);

    boolean deleteActor(Long id);
}
