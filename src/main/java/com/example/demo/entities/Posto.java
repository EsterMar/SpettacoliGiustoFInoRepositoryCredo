package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import jakarta.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name= "posto", schema= "spettacoliTeatrali")
public class Posto {

    @Id
    @GeneratedValue( strategy =  GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Basic
    @Column(name= "seatNumber", nullable = true)
    private int seat;

    @Basic
    @Column(name = "row_number", nullable = true)
    private int row;

    @Basic
    @Column(name= "available", nullable = true)
    private boolean available;

    @Version
    @Column(name = "version", nullable = false)
    @JsonIgnore
    private long version;


    @OneToOne(mappedBy = "posto", cascade= CascadeType.MERGE)
    @JsonIgnore
    private Biglietto ticket;

    @OneToMany(mappedBy= "posto", cascade= CascadeType.MERGE)
    @JsonIgnore
    private List<Evento> events;

    @ManyToOne
    @JoinColumn(name= "sala")
    private Sala sala;
}
