package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "awards")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Award {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String awardName; // Best Actor, Best OST
    private String category;
    private String eventName; // Baeksang, SBS Awards
    private Integer year;
    private String result; // Won / Nominated

    @ManyToOne
    @JoinColumn(name = "drama_id")
    private Drama drama;
}
