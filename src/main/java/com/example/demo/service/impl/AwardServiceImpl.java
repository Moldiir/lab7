package com.example.demo.service.impl;

import com.example.demo.dto.AwardDto;
import com.example.demo.entity.Award;
import com.example.demo.map.AwardMapper;
import com.example.demo.repository.AwardRepository;
import com.example.demo.service.AwardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AwardServiceImpl implements AwardService {

    private final AwardRepository awardRepository;
    private final AwardMapper awardMapper;

    @Override
    public List<AwardDto> getAwards() {
        List<Award> awards = awardRepository.findAll();
        return awardMapper.toDtoList(awards);
    }

    @Override
    public AwardDto getAward(Long id) {
        Award award = awardRepository.findById(id).orElse(null);
        if (award == null) {
            return null;
        }
        return awardMapper.toDto(award);
    }

    @Override
    public AwardDto addAward(AwardDto awardDto) {
        Award award = awardMapper.toEntity(awardDto);
        award.setId(null);
        Award saved = awardRepository.save(award);
        return awardMapper.toDto(saved);
    }

    @Override
    public AwardDto updateAward(AwardDto awardDto, Long id) {
        Award existing = awardRepository.findById(id).orElse(null);
        if (existing == null) {
            return null;
        }

        Award toSave = awardMapper.toEntity(awardDto);
        toSave.setId(id);

        Award updated = awardRepository.save(toSave);
        return awardMapper.toDto(updated);
    }

    @Override
    public boolean deleteAward(Long id) {
        if (!awardRepository.existsById(id)) {
            return false;
        }
        awardRepository.deleteById(id);
        return true;
    }
}
