package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;


@Data
@Entity
@Table(name= "biglietto")
public class Biglietto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "id", nullable = false)
    private int id;

    @Basic
    @Column(name = "price", nullable = true)
    private float price;

    @Basic
    @Column(name = "name", nullable = true)
    private String name;

    @ManyToOne
    @JoinColumn(name= "cliente")
    private Cliente cliente;

    @ManyToOne(cascade= CascadeType.MERGE)
    @JoinColumn(name= "evento")
    private Evento evento;

    @OneToOne
    @JoinColumn(name= "posto")
    @JsonIgnore
    private Posto posto;

    @Override
    public String toString() {
        return "Biglietto{" +
                "id=" + id +
                ", price=" + price +
                ", cliente=" + (cliente != null ? cliente.getId() : null) +
                ", evento=" + (evento != null ? evento.getId() : null) +
                ", posto=" + (posto != null ? posto.getId() : null) +
                '}';
    }
}
