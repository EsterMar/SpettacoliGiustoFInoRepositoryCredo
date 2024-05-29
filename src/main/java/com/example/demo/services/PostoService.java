package com.example.demo.services;

import com.example.demo.ParamAddTicket;
import com.example.demo.exceptions.TheEventIsSoldOutException;
import com.example.demo.exceptions.TheSeatIsNotAvailableException;
import com.example.demo.entities.Cliente;
import com.example.demo.entities.Evento;
import com.example.demo.entities.Posto;
import com.example.demo.entities.Sala;
import com.example.demo.repositories.EventoRepository;
import com.example.demo.repositories.PostoRepository;
import com.example.demo.repositories.SalaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class PostoService {

    @Autowired
    private PostoRepository postoRepository;

    @Autowired
    private BigliettoService bigliettoService;

    @Autowired
    private SalaRepository salaRepository;
    @Autowired
    private EventoService eventoService;

    @Transactional(readOnly=true)
    public int OccupiedSeats(Integer id_sala){
        return postoRepository.countOccupiedSeats(id_sala);
    }

    @Transactional(readOnly = true)
    public Posto showAllSeatsById(int id_posto){
        return postoRepository.findById(id_posto);
    }

    @Transactional(readOnly=true)
    public int countFreeSeats(Integer id_sala){
        Sala sala = salaRepository.findById(id_sala).orElse(null);
        if (sala != null) {
            return sala.getCapacity() - postoRepository.countOccupiedSeats(id_sala);
        } else {
            return 0; // ho un valore di default, se la sala non esiste
        }
    }

    @Transactional(readOnly=true)
    public List<Integer> getAvailableSeats(int id_sala){
        return postoRepository.getFreeSeats(id_sala);
    }

    @Transactional(readOnly=false)
    public Posto addSeat(ParamAddTicket pat) throws TheSeatIsNotAvailableException, TheEventIsSoldOutException {
       Evento id_evento = pat.getEvento();
        // Recupera il posto dall'ID
        Posto posto = postoRepository.findById(pat.getPostoId());
        id_evento.setPosto(posto);
        System.out.println("L'evento è: " + id_evento);
        Cliente id_cliente= pat.getCliente();
       System.out.println("Il cliente è: " + id_cliente);
       System.out.println("Il posto dell'evento è il seguente: "+id_evento.getPosto().toString());
      /* Posto seat= id_evento.getPosto();
       System.out.println("Il posto salvato è il seguente: "+seat.toString());*/
        float price= pat.getTicketPrice();
        System.out.println("Il prezzo è il seguente: "+price);
        String clientName= pat.getClientName();
        System.out.println("Il nome del cliente è il seguente: "+clientName);


        if(!eventoService.isAvailable(id_evento.getId()))
            throw new TheEventIsSoldOutException();
        if (!posto.isAvailable()) {
            throw new TheSeatIsNotAvailableException();
        }
        bigliettoService.chooseSeat(id_cliente, posto, id_evento, clientName, price);
        return posto;
    }



}
