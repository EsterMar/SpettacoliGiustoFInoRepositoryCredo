package com.example.demo.controllers;

import com.example.demo.ParamAddTicket;
import com.example.demo.exceptions.ClientDoesNotExistException;
import com.example.demo.exceptions.TheEventIsSoldOutException;
import com.example.demo.exceptions.TheSeatIsNotAvailableException;
import com.example.demo.entities.Cliente;
import com.example.demo.repositories.BigliettoRepository;
import com.example.demo.services.BigliettoService;
import com.example.demo.services.PostoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import jakarta.validation.Valid;
import java.util.Date;

@RestController
@RequestMapping("/biglietto")
public class BigliettoController {

    @Autowired
    private BigliettoService bigliettoService;

    @Autowired
    private BigliettoRepository bigliettoRepository;

    @Autowired
    private PostoService postoService;

    @PostMapping
    public ResponseEntity create (@RequestBody @Valid ParamAddTicket pat){
        try {
            return new ResponseEntity<>(postoService.addSeat(pat), HttpStatus.OK);
        } catch (TheSeatIsNotAvailableException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The seat"+pat.getPosto()+" is not available!", e);
        } catch (TheEventIsSoldOutException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The  event" +pat.getEvento()+"is sold out. There aren't free seats.", e);
        }

    }

    @GetMapping("/price")
    public ResponseEntity getTotalPrice(@RequestBody @Valid Cliente cliente){
        try{
            return new ResponseEntity((bigliettoService.totalPrice(cliente)), HttpStatus.OK);
        } catch (ClientDoesNotExistException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found",e);
        }
    }

    @GetMapping("/ticikets/{date}")
    public int getTicketsInData(@RequestParam ("date") @DateTimeFormat(pattern= "DD-MM-YYYY") Date date){
        return bigliettoRepository.countInDate(date);

    }

}
