package com.example.demo.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ActorDto {
    private Long id;
    private String fullName;
    private Integer age;
    private String country;
    private String agency;
    private Double popularity;
    private String biography;
}
