package com.example.demo.repositories;

import com.example.demo.entities.Spettacolo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface SpettacoloRepository extends JpaRepository<Spettacolo, Integer> {

    //dovrebbe essere corretto

    List<Spettacolo> findByTitle(String title);
    List<Spettacolo> findByGenre(String genre);
    List<Spettacolo> findAll();
    @Query("SELECT s FROM Spettacolo s WHERE s.teatro.id= :id_teatro")
    List<Spettacolo> findByIdTeatro(int id_teatro);


    @Query("SELECT s FROM Spettacolo s WHERE s.first_day <= :firstDay and :firstDay<=s.last_day")
    List<Spettacolo> findByFirstDayAfter(Date firstDay); //trovo gli spettacoli che vi sono dopo quella data (data compresa)

    @Query("SELECT SUM(s.price * :numBiglietti) FROM Spettacolo s WHERE s.id=:id_spettacolo")
    float calculatePriceByTickets(int id_spettacolo, int numBiglietti);

    @Query("SELECT s FROM Spettacolo s WHERE s.teatro.id= :id_teatro")
    Page<Spettacolo> findAllByTheaterId(int id_teatro, Pageable pageable);



}
