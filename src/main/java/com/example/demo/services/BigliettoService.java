package com.example.demo.services;

import com.example.demo.exceptions.ClientDoesNotExistException;
import com.example.demo.exceptions.TheSeatIsNotAvailableException;
import com.example.demo.entities.Biglietto;
import com.example.demo.entities.Cliente;
import com.example.demo.entities.Evento;
import com.example.demo.entities.Posto;
import com.example.demo.repositories.BigliettoRepository;
import com.example.demo.repositories.ClienteRepository;
import com.example.demo.repositories.PostoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BigliettoService {

    @Autowired
    private BigliettoRepository bigliettoRepository;
    @Autowired
    private PostoRepository postoRepository;
    @Autowired
    private ClienteRepository clienteRepository;

    @Transactional(readOnly = true)
    public float totalPrice(Cliente id_cliente) throws ClientDoesNotExistException {
        if(!clienteRepository.existsByEmail(id_cliente.getEmail()))
            throw new ClientDoesNotExistException();
        return bigliettoRepository.sumTheTotalPriceForClient(id_cliente);
    }

    @Transactional(readOnly = false)
    public Biglietto chooseSeat(Cliente id_cliente, Posto selected_seat, Evento id_evento) throws TheSeatIsNotAvailableException {
        if(!selected_seat.isAvailable())
            throw new TheSeatIsNotAvailableException();

        //faccio in modo che il posto risulti occupato, così nessuno può sceglierlo successivamente
        selected_seat.setAvailable(false);

        //creo il nuovo biglietto
        Biglietto ticket= new Biglietto();
        ticket.setEvento(id_evento);
        ticket.setCliente(id_cliente);
        ticket.setPosto(selected_seat);

        //salvo il biglietto
        ticket= bigliettoRepository.save(ticket);
        postoRepository.save(selected_seat);
        return ticket;
    }

}
