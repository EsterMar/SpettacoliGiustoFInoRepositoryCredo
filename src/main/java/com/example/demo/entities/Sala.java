package com.example.demo.entities;


import lombok.Data;

import jakarta.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name= "sala", schema= "spettacoliTeatrali")
public class Sala {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name= "room_number", nullable = false)
    private int room_number;

    @Basic
    @Column(name= "zone", nullable = true, length = 90)
    private String zone;

    @Basic
    @Column(name = "capacity", nullable = true)
    private int capacity;

    @OneToMany(mappedBy = "sala", cascade= CascadeType.MERGE)
    private List<Evento> events;

    @ManyToOne
    @JoinColumn(name= "teatro")
    private Teatro teatro;

    @OneToMany(mappedBy = "sala", cascade= CascadeType.MERGE)
    private List<Posto> seats;
}
