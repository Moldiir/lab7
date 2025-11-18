package com.example.demo.restController;

import com.example.demo.dto.AwardDto;
import com.example.demo.service.AwardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/awards")
public class AwardRestController {

    private final AwardService awardService;

    @GetMapping
    public ResponseEntity<?> getAwards() {
        List<AwardDto> awards = awardService.getAwards();
        if (awards.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(awards, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAward(@PathVariable("id") Long id) {
        AwardDto award = awardService.getAward(id);
        if (award == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(award);
    }

    @PostMapping
    public ResponseEntity<?> addAward(@RequestBody AwardDto awardDto) {
        AwardDto dto = awardService.addAward(awardDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAward(@PathVariable("id") Long id,
                                         @RequestBody AwardDto awardDto) {
        AwardDto dto = awardService.updateAward(awardDto, id);
        if (Objects.isNull(dto)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAward(@PathVariable("id") Long id) {
        boolean result = awardService.deleteAward(id);
        if (result) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
