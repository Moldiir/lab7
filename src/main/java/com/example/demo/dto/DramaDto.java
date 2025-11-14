package com.example.demo.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class DramaDto {
    private Long id;
    private String title;
    private String genre;
    private Integer episodes;
    private Integer year;
    private String country;
    private Double rating;
    private String description;
}
