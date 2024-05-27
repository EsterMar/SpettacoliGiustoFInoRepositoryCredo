package com.example.demo.repositories;

import com.example.demo.entities.Evento;
import com.example.demo.entities.Posto;
import com.example.demo.entities.Spettacolo;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface EventoRepository extends JpaRepository<Evento,Integer> {

    //dovrebbero essere giusti

    List<Evento> findByHours(String hours);
    List<Evento> findByData (Date data);

    @Query("SELECT e FROM Evento e WHERE e.spettacolo.title=:title")
    List<Evento> findAllByIdSpettacolo(String title);

    List<Evento> findAllByPosto(Posto posto);
    Evento findAllById(int evento);

    @Query("SELECT e from Evento e where e.data > ?1 and e.data < ?2")
    List<Evento> findByDataBetween (Date first_Day, Date last_Day);

    @Query("SELECT COUNT(e)>0 FROM Evento e JOIN e.sala s JOIN s.seats p WHERE  e.id=:id_evento and p.available=true") //devo assicurarmi che siano disponibili per tutto il tempo della ricerca fino alla prenotazione degli stessi
    boolean existsEventInRoomWithAvailableSeats(Integer id_evento); //considero se tra i posti di una sala che ospita l'evento
                                                                    //ve ne sia almeno uno libero
}
