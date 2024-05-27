package com.example.demo.controllers;

import com.example.demo.exceptions.ThereIsntThisSpectaclesException;
import com.example.demo.entities.Sala;
import com.example.demo.entities.Spettacolo;
import com.example.demo.services.SalaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/sala")
public class SalaController {

    @Autowired
    private SalaService salaService;




    @GetMapping("/Where/spectacles")
    public ResponseEntity getPlaceOfSpectacle(@RequestBody @Valid Spettacolo id_spettacolo){
        try{
            return new ResponseEntity(salaService.whereIsTheSpectacle(id_spettacolo), HttpStatus.OK);
        } catch (ThereIsntThisSpectaclesException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The spactacle"+id_spettacolo+"not found", e);
        }
    }

    @GetMapping("/seats")
    public int getNumberAllSeats(@RequestParam int id_sala){
        Sala sala = salaService.getById(id_sala);
        if (sala == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Room "+sala+" not found");
        }
        return salaService.numberOfAllTheSeats(sala);
    }

}
