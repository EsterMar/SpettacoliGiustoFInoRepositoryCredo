package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import jakarta.persistence.*;
import org.hibernate.annotations.AnyKeyJdbcType;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "spettacolo")

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
    @Column (name = "price", nullable = true, length = 500)
    private float price;

    @Basic
    @Column (name = "first_day", nullable = true)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date first_day;

    @Basic
    @Column (name = "last_day", nullable = true)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date last_day;

   /* @OneToMany(mappedBy = "spettacolo", cascade= CascadeType.MERGE)
    @JsonIgnore
    private List<Teatro> theatres;*/

    @ManyToOne
    @JoinColumn(name="teatro")
    private Teatro teatro;
    @OneToMany(mappedBy = "spettacolo", cascade= CascadeType.MERGE)
    @JsonIgnore
    private List<Evento> events;
}
