package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@Entity
@Table(name= "cliente")
//@ToString(exclude = "tickets")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "id", nullable = false)
    private int id;

    @Basic
    @Column(name= "name", nullable = true, length = 50)
    private String name;

    @Basic
    @Column(name= "surname", nullable = true, length = 50)
    private String surname;

    @Basic
    @Column(name= "telephone_number", nullable = true, length = 20)
    private String telephone_number;
    @Basic
    @Column(name= "email", nullable = true, length = 590)
    private String email;
    @Basic
    @Column(name= "address", nullable = true, length = 150)
    private String address;
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)//usato per far sì che le modifiche che si verificano su client, vengano riportate automaticamente anche su Tickets senza necessità di modificarle manualmente
    @JsonIgnore
    private List<Biglietto> tickets;

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", telephone_number='" + telephone_number + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

}
