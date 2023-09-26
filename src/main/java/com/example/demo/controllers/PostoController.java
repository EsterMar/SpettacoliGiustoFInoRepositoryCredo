package com.example.demo.controllers;

import com.example.demo.entities.Sala;
import com.example.demo.repositories.PostoRepository;
import com.example.demo.services.PostoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/posto")
public class PostoController {
    @Autowired
    private PostoService postoService;

    @Autowired
    private PostoRepository postoRepository;


    @GetMapping("/free")
    public boolean isThereFreeSeats(@RequestBody @Valid Sala id_sala){
        return postoService.FreeSeats(id_sala)>0;
    }

    @GetMapping("/occupied")
    public int getOccupiedSeats(@RequestBody @Valid Sala id_sala){
        return postoRepository.countOccupiedSeats(id_sala);
    }
}
