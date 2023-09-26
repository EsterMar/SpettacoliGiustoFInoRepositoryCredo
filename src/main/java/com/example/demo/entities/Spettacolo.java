package com.example.demo.entities;

import lombok.Data;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "spettacolo", schema= "spettacoliTeatrali")

public class Spettacolo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;


    @Basic
    @Column (name = "title", nullable = true, length = 50)
    private String title;

    @Basic
    @Column (name = "genre", nullable = true, length = 70)
    private String genre;

    @Basic
    @Column (name = "description", nullable = true, length = 500)
    private String description;

    @Basic
    @Column (name = "first_day", nullable = true)
    private Date first_day;

    @Basic
    @Column (name = "last_day", nullable = true)
    private Date last_day;

    @OneToMany(mappedBy = "spettacolo", cascade= CascadeType.MERGE)
    private List<Teatro> theatres;

    @OneToMany(mappedBy = "spettacolo", cascade= CascadeType.MERGE)
    private List<Evento> events;
}
