package com.example.demo.controllers;

import com.example.demo.ResponseMessage;
import com.example.demo.entities.Biglietto;
import com.example.demo.entities.Evento;
import com.example.demo.entities.Posto;
import com.example.demo.entities.Sala;
import com.example.demo.repositories.PostoRepository;
import com.example.demo.services.PostoService;
import org.antlr.v4.runtime.misc.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/posto")
public class PostoController {
    @Autowired
    private PostoService postoService;
    @Autowired
    private PostoRepository postoRepository;


    @GetMapping("/free")
    public boolean isThereFreeSeats(@RequestParam Integer sala){
        return postoService.countFreeSeats(sala)>0;
    }

    @GetMapping("/seats_by_id")
    public ResponseEntity showSeatsById(@RequestParam int id_posto) {
        Posto result = postoService.showAllSeatsById(id_posto);
        if (result == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage("Posto non trovato"));
        }
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/get_free_seats")
    public List<Integer> getAllFreSeats(@Valid @RequestParam int id_sala){
        return postoService.getAvailableSeats(id_sala);
    }




 /*   @GetMapping("/free2")
    public boolean isThereFreeSeats2(){
        Sala x = new Sala();
        x.setRoom_number(0);
        return postoService.FreeSeats(x)>0;
    }
*/
    @GetMapping("/occupied")
    public int getOccupiedSeats(@RequestParam Integer sala){
        return postoService.OccupiedSeats(sala);
    }


    @PutMapping("/updateAvailableState/{availablenewstate}")
    public ResponseEntity updateAvailableState(@PathVariable("availablenewstate") boolean availablenewstate, @RequestParam int id_posto) {
        Posto posto = postoService.showAllSeatsById(id_posto);
        if (posto == null) {
            return new ResponseEntity<>(new ResponseMessage("No seat found"), HttpStatus.OK);
        }
        posto.setAvailable(availablenewstate);
        postoRepository.save(posto);
        return new ResponseEntity<>(posto, HttpStatus.OK);
    }
}
