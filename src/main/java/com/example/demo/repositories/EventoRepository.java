package com.example.demo.repositories;

import com.example.demo.entities.Evento;
import com.example.demo.entities.Spettacolo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface EventoRepository extends JpaRepository<Evento,Integer> {

    List<Evento> findByHours(String hours);
    List<Evento> findByData (Date data);

    List<Evento> findAllBySpettacolo(Spettacolo id_spettacolo);

    List<Evento> findByDataBetween (Date first_Day, Date last_Day);

    @Query("SELECT COUNT(e)>0 FROM Evento e JOIN e.sala s JOIN s.seats p WHERE p.available=true and e.id=:id_evento")
    boolean existsEventInRoomWithAvailableSeats(Integer id_evento); //considero se tra i posti di una sala che ospita l'evento
                                                                    //ve ne sia almeno uno libero
}
