package com.example.demo.restController;

import com.example.demo.dto.DramaDto;
import com.example.demo.service.DramaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/dramas")
public class DramaRestController {

    private final DramaService dramaService;

    @GetMapping
    public ResponseEntity<?> getDramas() {
        List<DramaDto> dramas = dramaService.getDramas();
        if (dramas.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(dramas, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getDrama(@PathVariable("id") Long id) {
        DramaDto drama = dramaService.getDrama(id);
        if (drama == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(drama);
    }

    @PostMapping
    public ResponseEntity<?> addDrama(@RequestBody DramaDto dramaDto) {
        DramaDto dto = dramaService.addDrama(dramaDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateDrama(@PathVariable("id") Long id,
                                         @RequestBody DramaDto dramaDto) {
        DramaDto dto = dramaService.updateDrama(dramaDto, id);
        if (Objects.isNull(dto)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDrama(@PathVariable("id") Long id) {
        boolean result = dramaService.deleteDrama(id);
        if (result) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
