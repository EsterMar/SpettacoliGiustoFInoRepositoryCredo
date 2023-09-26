package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;


@Data
@Entity
@Table(name= "Biglietto", schema= "spettacoliTeatrali")
public class Biglietto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "id", nullable = false)
    private int id;

    @Basic
    @Column(name = "price", nullable = true)
    private float price;

    @ManyToOne
    @JoinColumn(name= "cliente")
    @JsonIgnore
    private Cliente cliente;

    @ManyToOne(cascade= CascadeType.MERGE)
    @JoinColumn(name= "evento")
    private Evento evento;

    @OneToOne
    @JoinColumn(name= "posto")
    private Posto posto;
}
