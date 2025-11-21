package com.example.demo.service.impl;

import com.example.demo.dto.DramaDto;
import com.example.demo.entity.Award;
import com.example.demo.entity.Drama;
import com.example.demo.map.DramaMapper;
import com.example.demo.repository.ActorRepository;
import com.example.demo.repository.AwardRepository;
import com.example.demo.repository.DramaRepository;
import com.example.demo.service.DramaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DramaServiceImpl implements DramaService {

    private final DramaRepository dramaRepository;
    private final DramaMapper dramaMapper;
    private final ActorRepository actorRepository;
    private final AwardRepository awardRepository;

    @Override
    public List<DramaDto> getDramas() {
        List<Drama> dramas = dramaRepository.findAll();
        return dramaMapper.toDtoList(dramas);
    }

    @Override
    public DramaDto getDrama(Long id) {
        Drama drama = dramaRepository.findById(id).orElse(null);
        if (drama == null) {
            return null;
        }
        return dramaMapper.toDto(drama);
    }

    @Override
    public DramaDto addDrama(DramaDto dramaDto) {
        Drama drama = dramaMapper.toEntity(dramaDto);
        drama.setId(null);                     // на всякий случай
        Drama saved = dramaRepository.save(drama);
        return dramaMapper.toDto(saved);
    }
    @Override
    public DramaDto updateDrama(DramaDto dto, Long id) {
        Drama existing = dramaRepository.findById(id).orElse(null);
        if (existing == null) {
            return null;
        }

        // ----- простые поля -----
        existing.setTitle(dto.getTitle());
        existing.setGenre(dto.getGenre());
        existing.setYear(dto.getYear());
        existing.setRating(dto.getRating());

        // ----- АКТЁРЫ -----
        if (dto.getActors() != null) {
            var actorIds = dto.getActors().stream()
                    .map(a -> a.getId())
                    .toList();

            existing.setActors(actorRepository.findAllById(actorIds));
        }

        // ----- НАГРАДЫ -----
        if (dto.getAwards() != null) {
            var awardIds = dto.getAwards().stream()
                    .map(a -> a.getId())
                    .toList();

            var awards = awardRepository.findAllById(awardIds);

            for (Award award : awards) {
                award.setDrama(existing);
            }

            existing.setAwards(awards);
        }

        Drama updated = dramaRepository.save(existing);
        return dramaMapper.toDto(updated);
    }

    @Override
    public boolean deleteDrama(Long id) {
        if (!dramaRepository.existsById(id)) {
            return false;
        }
        dramaRepository.deleteById(id);
        return true;
    }
}
