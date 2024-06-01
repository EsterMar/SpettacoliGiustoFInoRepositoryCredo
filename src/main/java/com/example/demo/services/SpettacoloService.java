package com.example.demo.services;

import com.example.demo.entities.Spettacolo;
import com.example.demo.exceptions.ThereIsntThisSpectaclesException;
import com.example.demo.repositories.SpettacoloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class SpettacoloService {

    @Autowired
    private SpettacoloRepository spettacoloRepository;

    @Transactional(readOnly = true)
    public List<Spettacolo> showAllShows(){
        return spettacoloRepository.findAll();
    }
    @Transactional(readOnly = true)
    public Page<Spettacolo> findAllByTheater(int id_teatro, Pageable pageable) {
        return spettacoloRepository.findAllByTheaterId(id_teatro, pageable);
    }

    @Transactional(readOnly = true)
    public List<Spettacolo> showByTheater(int id_teatro){
        return spettacoloRepository.findByIdTeatro(id_teatro);
    }

    @Transactional(readOnly = true)
    public List<Spettacolo> showByTitle(String titolo ){
        return spettacoloRepository.findByTitle(titolo);
    }

    @Transactional(readOnly = true)
    public float priceByTicket(int id_spettacolo, int numBiglietti ) throws ThereIsntThisSpectaclesException {
        return spettacoloRepository.calculatePriceByTickets(id_spettacolo, numBiglietti);
    }
   /* @Transactional(readOnly = true)
    public List<Spettacolo> showAllShows(int pageNumber, int pageSize, String sortBy) {
        Pageable paging = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
        Page<Spettacolo> pagedResult = spettacoloRepository.findAll(paging);
        if ( pagedResult.hasContent() ) {
            return pagedResult.getContent();
        }
        else {
            return new ArrayList<>();
        }
    }*/




}
