package com.example.demo.repositories;

import com.example.demo.entities.Spettacolo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface SpettacoloRepository extends JpaRepository<Spettacolo, Integer> {

    List<Spettacolo> findByTitle(String title);
    List<Spettacolo> findByGenre(String genre);

    @Query("SELECT s FROM Spettacolo s WHERE s.first_day > :firstDay")
    List<Spettacolo> findByFirstDayAfter(@Param("firstDay") Date firstDay);




}
