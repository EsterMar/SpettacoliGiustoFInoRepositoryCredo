package com.example.demo.services;

import com.example.demo.exceptions.ThereIsntThisSpectaclesException;
import com.example.demo.entities.Spettacolo;
import com.example.demo.entities.Teatro;
import com.example.demo.repositories.TeatroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.data.domain.Pageable;
import java.util.List;

@Service
public class TeatroService {

    @Autowired
    private TeatroRepository teatroRepository;

    @Transactional(readOnly = true)
    public List<Teatro> getTheatres (Spettacolo id_spettacolo) throws ThereIsntThisSpectaclesException {
        List<Teatro> theatres = teatroRepository.findAllBySpettacolo(id_spettacolo);
        if(theatres.isEmpty())
            throw new ThereIsntThisSpectaclesException();
        return theatres;
    }

    @Transactional(readOnly = true)
    public List<Teatro> getTheatres (String city){
        return teatroRepository.findAllByCity(city);
    }


    public Page<Teatro> getTheatresByCityPaged(String city, Pageable pageable) {
        return teatroRepository.findByCity(city, pageable);
    }



    //lascio?
    @Transactional(readOnly = true)
    public boolean isThereThisShow(Spettacolo id_spettacolo){
        return teatroRepository.existsBySpettacolo(id_spettacolo);
    }
}
