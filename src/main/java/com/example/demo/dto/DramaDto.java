package com.example.demo.dto;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DramaDto {

    private Long id;
    private String title;
    private String genre;
    private Integer year;
    private Double rating;

    private List<ActorShortDto> actors; // не показываем biography, popularity
    private List<AwardDto> awards;      // тоже чистые DTO
}

