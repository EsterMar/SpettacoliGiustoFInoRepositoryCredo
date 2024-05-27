package com.example.demo.controllers;

import com.example.demo.ResponseMessage;
import com.example.demo.entities.Evento;
import com.example.demo.entities.Posto;
import com.example.demo.entities.Spettacolo;
import com.example.demo.exceptions.DateWrongRangeException;
import com.example.demo.services.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/evento")
public class EventoController {

    @Autowired
    private EventoService eventoService;


    @GetMapping("/paged")
    public ResponseEntity getAll(@RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber, @RequestParam(value = "pageSize", defaultValue = "3") int pageSize, @RequestParam(value = "sortBy", defaultValue = "id") String sortBy) {
        List<Evento> result =  eventoService.showAllEvents(pageNumber, pageSize, sortBy);
        if ( result.size() <= 0 ) {
            return new ResponseEntity<>(new ResponseMessage("No results!"), HttpStatus.OK);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    @GetMapping("/all")
    public ResponseEntity getAll() {

        List<Evento> result =  eventoService.showAllEvents();
        if ( result.size() <= 0 ) {
            return new ResponseEntity<>(new ResponseMessage("No results!"), HttpStatus.OK);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/search/by_seat")
    public ResponseEntity showAllEventsBySeat(@RequestBody @Valid Posto posto) {
        List<Evento> result = eventoService.showAllEventsBySeat(posto);
        if ( result.size() <= 0 ) {
            return new ResponseEntity<>(new ResponseMessage("No results!"), HttpStatus.OK);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    //usato per prova
    @GetMapping("/seats_by_events")
    public ResponseEntity showSeatsByEvents(@RequestParam int id_evento) {
        Evento e= eventoService.showAllEventsByIdEvents(id_evento);
        Posto result= e.getPosto();
        if ( result.equals(null)) {
            return new ResponseEntity<>(new ResponseMessage("No results!"), HttpStatus.OK);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    @GetMapping("/search/by_show")
    public ResponseEntity getByShow2(@RequestParam String title) {
        List<Evento> result = eventoService.showAllEventsByShow(title);
        if ( result.size() <= 0 ) {
            return new ResponseEntity<>(new ResponseMessage("No results!"), HttpStatus.OK);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/{startDate}/{endDate}")
    public ResponseEntity getEventsInPeriod(@Valid @PathVariable("startDate") @DateTimeFormat(pattern = "dd-MM-yyyy") Date start, @PathVariable("endDate") @DateTimeFormat(pattern = "dd-MM-yyyy") Date end) {
        try {
            List<Evento> result = eventoService.showEventsInPeriod(start, end);
            if ( result.size() <= 0 ) {
                return new ResponseEntity<>(new ResponseMessage("No results!"), HttpStatus.OK);
            }
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (DateWrongRangeException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Start date must be previous end date!", e);
        }
    }
}
