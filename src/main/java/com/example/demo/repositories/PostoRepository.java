package com.example.demo.repositories;

import com.example.demo.entities.Evento;
import com.example.demo.entities.Posto;
import com.example.demo.entities.Sala;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostoRepository extends JpaRepository<Posto, Integer> {

    //dovrebbe essere corretto

    List<Posto> findByRow(int row_number);
    List<Posto> findByRowAndSeat(int row_number, int seat_number);
    Posto findById(int posto);


    //@Query(" SELECT COUNT(p) FROM Posto p WHERE p.available = false and p.sala = ?1 ")
    @Query("SELECT COUNT(p) FROM Sala s JOIN s.seats p WHERE s.room_number = ?1 and p.available = false")
    int countOccupiedSeats(Integer sala); //conta il numero di posti occupati nella sala specificata. Da passare in SalaRepository?
}
