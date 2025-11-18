package com.example.demo.service.impl;

import com.example.demo.dto.ActorDto;
import com.example.demo.entity.Actor;
import com.example.demo.map.ActorMapper;
import com.example.demo.repository.ActorRepository;
import com.example.demo.service.ActorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ActorServiceImpl implements ActorService {

    private final ActorRepository actorRepository;
    private final ActorMapper actorMapper;

    @Override
    public List<ActorDto> getActors() {
        List<Actor> actors = actorRepository.findAll();
        return actorMapper.toDtoList(actors);
    }

    @Override
    public ActorDto getActor(Long id) {
        Actor actor = actorRepository.findById(id).orElse(null);
        if (actor == null) {
            return null;
        }
        return actorMapper.toDto(actor);
    }

    @Override
    public ActorDto addActor(ActorDto actorDto) {
        Actor actor = actorMapper.toEntity(actorDto);
        actor.setId(null);
        Actor saved = actorRepository.save(actor);
        return actorMapper.toDto(saved);
    }

    @Override
    public ActorDto updateActor(ActorDto actorDto, Long id) {
        Actor existing = actorRepository.findById(id).orElse(null);
        if (existing == null) {
            return null;
        }

        Actor toSave = actorMapper.toEntity(actorDto);
        toSave.setId(id);

        Actor updated = actorRepository.save(toSave);
        return actorMapper.toDto(updated);
    }

    @Override
    public boolean deleteActor(Long id) {
        if (!actorRepository.existsById(id)) {
            return false;
        }
        actorRepository.deleteById(id);
        return true;
    }
}
