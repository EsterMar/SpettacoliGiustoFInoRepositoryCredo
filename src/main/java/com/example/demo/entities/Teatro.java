package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import jakarta.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name= "teatro")
public class Teatro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "id", nullable = false)
    private int id;

    @Basic
    @Column(name= "name", nullable = true, length = 90)
    private String name;
    @Basic
    @Column(name= "city", nullable = true, length = 90)
    private String city;

    @Basic
    @Column(name= "address", nullable = true, length = 150)
    private String address;

    @Basic
    @Column(name= "telephone_number", nullable = true, length = 20)
    private String telephone_number;

    @OneToMany(mappedBy = "teatro", cascade= CascadeType.MERGE)
    @JsonIgnore
    private List<Sala> rooms;

    @OneToMany(mappedBy = "teatro", cascade= CascadeType.MERGE)
    @JsonIgnore
    private List<Spettacolo> spettacolo;

//      @ManyToOne
    //    @JoinColumn(name= "spettacolo")
  //  private Spettacolo spettacolo;

}
