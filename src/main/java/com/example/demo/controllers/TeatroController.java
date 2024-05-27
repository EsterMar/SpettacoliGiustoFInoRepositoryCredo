package com.example.demo.controllers;

import com.example.demo.ResponseMessage;
import com.example.demo.exceptions.ThereIsntThisSpectaclesException;
import com.example.demo.entities.Spettacolo;
import com.example.demo.entities.Teatro;
import com.example.demo.services.TeatroService;
import org.springframework.beans.factory.annotation.Autowired;
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


    @GetMapping("/search/by_city")
    public List<Teatro> getTheater (@RequestParam String city){
        return teatroService.getTheatres(city);
    }
}
