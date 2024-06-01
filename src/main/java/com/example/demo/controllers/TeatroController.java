package com.example.demo.controllers;

import com.example.demo.ResponseMessage;
import com.example.demo.exceptions.ThereIsntThisSpectaclesException;
import com.example.demo.entities.Spettacolo;
import com.example.demo.entities.Teatro;
import com.example.demo.services.TeatroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/teatro")
public class TeatroController {

    @Autowired
    private TeatroService teatroService;

    @GetMapping("/shows")
    public ResponseEntity getTheater(@RequestBody @Valid Spettacolo spettacolo) {
        try {
            List<Teatro> theaters = teatroService.getTheatres(spettacolo);
            if (theaters.size() <= 0)
                return new ResponseEntity(new ResponseMessage("No result!"), HttpStatus.OK);
            return new ResponseEntity(theaters, HttpStatus.OK);
        } catch (ThereIsntThisSpectaclesException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Theater not found", e);
        }
    }


    @GetMapping("/search/by_city_paged")
    public ResponseEntity getTheater(@RequestParam String city,
            @RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber,
             @RequestParam(value = "pageSize", defaultValue = "3") int pageSize,
             @RequestParam(value = "sortBy", defaultValue = "id") String sortBy) {


        Pageable paging = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
        Page<Teatro> pagedResult = teatroService.getTheatresByCityPaged(city, paging);

        if (pagedResult.hasContent()) {
            return new ResponseEntity<>(pagedResult.getContent(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ResponseMessage("No results!"), HttpStatus.OK);
        }
    }


    @GetMapping("/search/by_city")
    public List<Teatro> getTheater (@RequestParam String city){
        return teatroService.getTheatres(city);
    }
}
