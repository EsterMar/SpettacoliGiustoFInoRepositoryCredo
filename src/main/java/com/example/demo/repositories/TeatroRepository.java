package com.example.demo.repositories;

import com.example.demo.entities.Spettacolo;
import com.example.demo.entities.Teatro;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Pageable;
import java.util.List;


@Repository
public interface TeatroRepository extends JpaRepository<Teatro, Integer> {

    //corretto per forza

    List<Teatro> findAllBySpettacolo (Spettacolo spettacolo);
    List<Teatro> findAllByCity (String city);

    @Query("SELECT t FROM Teatro t WHERE t.city= :city")
    Page<Teatro>findByCity(String city, Pageable pageable);

    @Query("SELECT t FROM Teatro t WHERE t.city= :city")
    Page<Spettacolo> findAllByCity(String city, Pageable pageable);

    boolean existsBySpettacolo (Spettacolo spettacolo);//quello spettacolo è in quel teatro?
}
