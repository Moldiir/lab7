package com.example.demo.service.impl;
import com.example.demo.dto.DramaDto;
import com.example.demo.entity.Drama;
import com.example.demo.repository.DramaRepository;
import com.example.demo.service.DramaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DramaServiceImpl implements DramaService {

    private final DramaRepository dramaRepository;

    @Override
    public List<DramaDto> getDramas() {
        List<Drama> dramas = dramaRepository.findAll();
        List<DramaDto> dramasDto = new ArrayList<>();
        dramas.forEach(drama -> {
            dramasDto.add(toDto(drama));
        });
        return dramasDto;
    }

    @Override
    public DramaDto getDrama(Long id) {
        Drama drama = dramaRepository.findById(id).orElse(null);
        if (drama == null) {
            return null;
        }
        return toDto(drama);
    }

    @Override
    public DramaDto addDrama(DramaDto dramaDto) {
        Drama drama = toEntity(dramaDto);
        Drama addedDrama = dramaRepository.save(drama);
        return toDto(addedDrama);
    }

    @Override
    public DramaDto updateDrama(DramaDto dramaDto, Long id) {
        DramaDto dto = getDrama(id);
        if (dto == null) {
            return null;
        }


        if (dramaDto.getTitle() != null) {
            dto.setTitle(dramaDto.getTitle());
        }
        if (dramaDto.getGenre() != null) {
            dto.setGenre(dramaDto.getGenre());
        }
        if (dramaDto.getEpisodes() != null && dramaDto.getEpisodes() > 0) {
            dto.setEpisodes(dramaDto.getEpisodes());
        }
        if (dramaDto.getYear() != null && dramaDto.getYear() > 0) {
            dto.setYear(dramaDto.getYear());
        }
        if (dramaDto.getCountry() != null) {
            dto.setCountry(dramaDto.getCountry());
        }
        if (dramaDto.getRating() != null && dramaDto.getRating() >= 0) {
            dto.setRating(dramaDto.getRating());
        }
        if (dramaDto.getDescription() != null) {
            dto.setDescription(dramaDto.getDescription());
        }

        Drama drama = dramaRepository.save(toEntity(dto));
        return toDto(drama);
    }

    @Override
    public boolean deleteDrama(Long id) {
        DramaDto dramaDto = getDrama(id);
        if (dramaDto == null) {
            return false;
        }
        dramaRepository.deleteById(id);
        return true;
    }


    public DramaDto toDto(Drama drama) {
        if (drama == null) return null;

        return DramaDto.builder()
                .id(drama.getId())
                .title(drama.getTitle())
                .genre(drama.getGenre())
                .episodes(drama.getEpisodes())
                .year(drama.getYear())
                .country(drama.getCountry())
                .rating(drama.getRating())
                .description(drama.getDescription())
                .build();
    }

    public Drama toEntity(DramaDto dramaDto) {
        if (dramaDto == null) return null;

        return Drama.builder()
                .id(dramaDto.getId())
                .title(dramaDto.getTitle())
                .genre(dramaDto.getGenre())
                .episodes(dramaDto.getEpisodes())
                .year(dramaDto.getYear())
                .country(dramaDto.getCountry())
                .rating(dramaDto.getRating())
                .description(dramaDto.getDescription())
                .build();
    }
}
