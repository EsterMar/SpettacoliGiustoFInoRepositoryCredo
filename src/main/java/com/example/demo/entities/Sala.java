package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import jakarta.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name= "sala")
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
    @JsonIgnore
    private List<Evento> events;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name= "teatro")
    private Teatro teatro;

    @OneToMany(mappedBy = "sala", cascade= CascadeType.MERGE)
    @JsonIgnore
    private List<Posto> seats;

}
