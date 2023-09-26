package com.example.demo.services;

import com.example.demo.ParamAddTicket;
import com.example.demo.exceptions.TheEventIsSoldOutException;
import com.example.demo.exceptions.TheSeatIsNotAvailableException;
import com.example.demo.entities.Cliente;
import com.example.demo.entities.Evento;
import com.example.demo.entities.Posto;
import com.example.demo.entities.Sala;
import com.example.demo.repositories.PostoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class PostoService {

    @Autowired
    private PostoRepository postoRepository;

    @Autowired
    private BigliettoService bigliettoService;

    @Autowired
    private EventoService eventoService;

    @Transactional(readOnly=true)
    public int OccupiedSeats(Sala id_sala){
        return postoRepository.countOccupiedSeats(id_sala);
    }

    @Transactional(readOnly=true)
    public int FreeSeats (Sala id_sala){
        return id_sala.getCapacity()- postoRepository.countOccupiedSeats(id_sala);
    }

    @Transactional(readOnly=false)
    public Posto addSeat(ParamAddTicket pat) throws TheSeatIsNotAvailableException, TheEventIsSoldOutException {
       Evento id_evento = pat.getEvento();
       Posto seat= pat.getPosto();
       Cliente id_cliente= pat.getCliente();

        if(!eventoService.isAvailable(id_evento.getId()))
            throw new TheEventIsSoldOutException();
        if (!seat.isAvailable()) {
            throw new TheSeatIsNotAvailableException();
        }
        bigliettoService.chooseSeat(id_cliente, seat, id_evento);
        return seat;
    }

}
