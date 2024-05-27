package com.example.demo.controllers;

import com.example.demo.ParamAddTicket;
import com.example.demo.ResponseMessage;
import com.example.demo.entities.Biglietto;
import com.example.demo.entities.Evento;
import com.example.demo.entities.Posto;
import com.example.demo.exceptions.ClientDoesNotExistException;
import com.example.demo.exceptions.EventDoesNotExistException;
import com.example.demo.exceptions.TheEventIsSoldOutException;
import com.example.demo.exceptions.TheSeatIsNotAvailableException;
import com.example.demo.entities.Cliente;
import com.example.demo.repositories.BigliettoRepository;
import com.example.demo.repositories.PostoRepository;
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
import java.util.List;

@RestController
@RequestMapping("/biglietto")
public class BigliettoController {

    @Autowired
    private BigliettoService bigliettoService;

    @Autowired
    private BigliettoRepository bigliettoRepository;

    @Autowired
    private PostoService postoService;
    @Autowired
    private PostoRepository postoRepository;

    @PostMapping("/create")
    public ResponseEntity create (@RequestBody @Valid ParamAddTicket pat){
        try {
            return new ResponseEntity<>(postoService.addSeat(pat), HttpStatus.OK);
        } catch (TheSeatIsNotAvailableException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The seat"+pat.getEvento().getPosto()+" is not available!", e);
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


  /* @GetMapping("/price_by_numberTickets")
    public ResponseEntity getTotalPriceByNumberOfTickets(@RequestParam int numeroBiglietti) {
            return new ResponseEntity((bigliettoService.calculateTotalPrice(numeroBiglietti)), HttpStatus.OK);
    }*/



    @GetMapping("/tickets/{date}")
    public int getTicketsInData(@Valid @PathVariable ("date") @DateTimeFormat(pattern= "yyyy-MM-dd") Date date){
        return bigliettoRepository.countInDate(date);
    }

    @GetMapping("/seat")
    public ResponseEntity getTicketsBySeat(@Valid @RequestBody Posto posto){
        List<Biglietto>b= bigliettoRepository.findByPosto(posto);
        if(b.equals(null)){
            return new ResponseEntity<>(new ResponseMessage("No tickets found for the seat"), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(b, HttpStatus.OK);
        }
    }

    //ho sbagliato ad inserire il prezzo del biglietto una volta e ho dovuto creare un metodo per modificarlo
    @PutMapping("/updatePrice/{bigliettoId}/{newPrice}")
    public ResponseEntity updatePrice(@PathVariable("bigliettoId") int bigliettoId, @PathVariable("newPrice") float newPrice) {
        Biglietto biglietto = bigliettoRepository.findById(bigliettoId);
        if (biglietto == null) {
            return new ResponseEntity<>(new ResponseMessage("No tickets found for the seat"), HttpStatus.OK);
        }
        biglietto.setPrice(newPrice);
        bigliettoRepository.save(biglietto);
        System.out.println("II biglietto sarà: "+biglietto);
        return new ResponseEntity<>(biglietto, HttpStatus.OK);
    }


    @DeleteMapping("/delete_by_client")
    public ResponseEntity deleteTicketsByCliente(@Valid @RequestBody Cliente cliente){
        List<Biglietto> deletedTickets = bigliettoService.deleteTicketsByCliente(cliente);
        for (Biglietto ticket : deletedTickets) {
            Posto posto = ticket.getPosto();
            posto.setAvailable(true); // Imposta il posto come disponibile
            postoRepository.save(posto); // Salva le modifiche al posto
        }
        if (deletedTickets.isEmpty()) {
            return new ResponseEntity<>(new ResponseMessage("No tickets found for the client"), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(deletedTickets, HttpStatus.OK);
        }
    }


}
