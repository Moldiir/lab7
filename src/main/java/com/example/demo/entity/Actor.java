package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@Table(name = "actors")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Actor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50)
    private String fullName;

    private Integer age;
    private String country;
    private String agency;
    private Double popularity;

    @Column(length = 1000)
    private String biography;

    @ManyToMany(mappedBy = "actors")
    private List<Drama> dramas;
}
