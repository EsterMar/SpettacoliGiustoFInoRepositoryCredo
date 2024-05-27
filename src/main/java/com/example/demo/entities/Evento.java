package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name= "evento")
public class Evento {

    @Id
    @GeneratedValue( strategy =  GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Basic
    @Column(name= "hours", nullable = true, length = 50)
    private String hours;

    @Basic
    @Column(name= "data", nullable = true)
    private Date data;


    @OneToMany(mappedBy = "evento",cascade= CascadeType.MERGE)
    @JsonIgnore
    private List<Biglietto> tickets;

    @ManyToOne
    @JoinColumn(name= "posto")
    private Posto posto;

    @ManyToOne
    @JoinColumn(name= "sala")
    private Sala sala;

    @ManyToOne
    @JoinColumn(name= "spettacolo")
    private Spettacolo spettacolo;

    @Override
    public String toString() {
        return "Evento{" +
                "id=" + id +
                ", hours='" + hours + '\'' +
                ", data=" + data +
                ", posto=" + (posto != null ? posto.getId() : null) +
                '}';
    }
}
