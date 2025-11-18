package com.example.demo.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@Table(name = "dramas")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Drama {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String genre;
    private Integer episodes;
    private Integer year;
    private String country;
    private Double rating;

    @Column(length = 1000)
    private String description;

    @ManyToMany
    @JoinTable(
            name = "drama_actors",
            joinColumns = @JoinColumn(name = "drama_id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id")
    )
    private List<Actor> actors;

    @OneToMany(mappedBy = "drama", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Award> awards;
}
