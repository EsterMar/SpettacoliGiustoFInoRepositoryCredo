package com.example.demo.repositories;

import com.example.demo.entities.Sala;
import com.example.demo.entities.Spettacolo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalaRepository extends JpaRepository<Sala, Integer> {
    List<Sala> findByZone (String zone);

    //boolean existsSalaBySpettacolo (Spettacolo id_spettacolo); //oppure va fatto in spettacolo? Credo di no

    @Query("SELECT s FROM Sala s WHERE s.room_number IN (SELECT e.sala FROM Evento e WHERE e.spettacolo= ?1)")
    List<Sala> findSalaBySpettacolo (Spettacolo id_spettacolo);

}
