package com.example.demo.restController;

import com.example.demo.dto.ActorDto;
import com.example.demo.service.ActorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/actors")
public class ActorRestController {

    private final ActorService actorService;

    @GetMapping
    public ResponseEntity<?> getActors() {
        List<ActorDto> actors = actorService.getActors();
        if (actors.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(actors, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getActor(@PathVariable("id") Long id) {
        ActorDto actor = actorService.getActor(id);
        if (actor == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(actor);
    }

    @PostMapping
    public ResponseEntity<?> addActor(@RequestBody ActorDto actorDto) {
        ActorDto dto = actorService.addActor(actorDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateActor(@PathVariable("id") Long id,
                                         @RequestBody ActorDto actorDto) {
        ActorDto dto = actorService.updateActor(actorDto, id);
        if (Objects.isNull(dto)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteActor(@PathVariable("id") Long id) {
        boolean result = actorService.deleteActor(id);
        if (result) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
