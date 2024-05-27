package com.example.demo.services;

import com.example.demo.exceptions.ThereIsntThisSpectaclesException;
import com.example.demo.entities.Sala;
import com.example.demo.entities.Spettacolo;
import com.example.demo.repositories.SalaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SalaService {

    @Autowired
    private SalaRepository salaRepository;

    @Transactional(readOnly = true)
    public List<Sala>  whereIsTheSpectacle(Spettacolo id_spettacolo) throws ThereIsntThisSpectaclesException {
        List<Sala> rooms = salaRepository.findSalaBySpettacolo(id_spettacolo);
        if (rooms.isEmpty())
            throw new ThereIsntThisSpectaclesException();
        return rooms;
    }

    @Transactional(readOnly = true)
    public int numberOfAllTheSeats(Sala id_sala){
        return id_sala.getCapacity();
    }

    @Transactional(readOnly = true)
    public Sala getById(int id) {
        return salaRepository.findById(id);
    }


}
