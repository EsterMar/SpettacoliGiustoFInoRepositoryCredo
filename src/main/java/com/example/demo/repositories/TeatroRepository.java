package com.example.demo.repositories;

import com.example.demo.entities.Spettacolo;
import com.example.demo.entities.Teatro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeatroRepository extends JpaRepository<Teatro, Integer> {

    //corretto per forza

    List<Teatro> findAllBySpettacolo (Spettacolo spettacolo);
    List<Teatro> findAllByCity (String city);

    boolean existsBySpettacolo (Spettacolo spettacolo);//quello spettacolo è in quel teatro?
}
