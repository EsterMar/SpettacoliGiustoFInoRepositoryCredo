package com.example.demo.controllers;

import com.example.demo.ResponseMessage;
import com.example.demo.entities.Evento;
import com.example.demo.entities.Spettacolo;
import com.example.demo.exceptions.ClientDoesNotExistException;
import com.example.demo.exceptions.ThereIsntThisSpectaclesException;
import com.example.demo.repositories.SpettacoloRepository;
import com.example.demo.services.SpettacoloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;


import jakarta.validation.Valid;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/spettacolo")
public class SpettacoloController {

    @Autowired
    private SpettacoloService spettacoloService;

    @Autowired
    private SpettacoloRepository spettacoloRepository;


    @GetMapping("/all")
    public ResponseEntity getAll() {

        List<Spettacolo> result =  spettacoloService.showAllShows();
        if ( result.size() <= 0 ) {
            return new ResponseEntity<>(new ResponseMessage("No results!"), HttpStatus.OK);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/search/paged")
    public ResponseEntity<?> getAllShowsByTheater(@RequestParam int id_teatro,
            @RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "3") int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "id") String sortBy) {
        Pageable paging = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
        Page<Spettacolo> pagedResult = spettacoloService.findAllByTheater(id_teatro, paging);

        if (pagedResult.hasContent()) {
            return new ResponseEntity<>(pagedResult.getContent(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ResponseMessage("No results!"), HttpStatus.OK);
        }
    }


    @GetMapping("/search/byTitle")
    public ResponseEntity getByTitle(@Valid @RequestParam String titolo) {
        List<Spettacolo> result =  spettacoloService.showByTitle(titolo);
        if ( result.size() <= 0 ) {
            return new ResponseEntity<>(new ResponseMessage("No results!"), HttpStatus.OK);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/search/by_theater")
    public ResponseEntity getByTheater(@Valid @RequestParam int id_teatro) {
        List<Spettacolo> result =  spettacoloService.showByTheater(id_teatro);
        if ( result.size() <= 0 ) {
            return new ResponseEntity<>(new ResponseMessage("No results!"), HttpStatus.OK);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/{startDate}")
    public List<Spettacolo> getSpectaclesAfterDate(@Valid @PathVariable ("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date start){
        return spettacoloRepository.findByFirstDayAfter(start);
    }

    @GetMapping("/price_by_numberTickets")
    public ResponseEntity getPriceByTicket(@Valid @RequestParam int id_spettacolo, @Valid @RequestParam int numBiglietti ) {
        try{
            return new ResponseEntity((spettacoloService.priceByTicket(id_spettacolo, numBiglietti)), HttpStatus.OK);
        } catch (ThereIsntThisSpectaclesException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found",e);
        }
    }

}
