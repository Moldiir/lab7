package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AwardDto {
    private Long id;
    private String awardName;
    private String category;
    private String eventName;
    private Integer year;
    private String result;
}

