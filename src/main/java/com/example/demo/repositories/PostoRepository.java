package com.example.demo.repositories;

import com.example.demo.entities.Posto;
import com.example.demo.entities.Sala;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostoRepository extends JpaRepository<Posto, Integer> {

    List<Posto> findByRow(int row_number);
    List<Posto> findByRowAndSeat(int row_number, int seat_number);

    @Query(" SELECT COUNT(p) FROM Posto p WHERE p.available = false and p.sala = ?1 ")
    int countOccupiedSeats(Sala id_sala); //conta il numero di posti occupati
}
