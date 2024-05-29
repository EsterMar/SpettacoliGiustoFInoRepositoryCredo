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
import com.example.demo.repositories.SpettacoloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
public class BigliettoService {

    @Autowired
    private BigliettoRepository bigliettoRepository;
    @Autowired
    private PostoRepository postoRepository;
    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private SpettacoloRepository spettacoloRepository;




    //private static final float PRICE_PER_TICKET = 12.0f;

  /*  @Transactional(readOnly = true)
    public float calculateTotalPrice(int numeroBiglietti) {
        return PRICE_PER_TICKET * numeroBiglietti;
    }*/




    @Transactional(readOnly = true)
    public float totalPrice(Cliente id_cliente) throws ClientDoesNotExistException {
        if( !clienteRepository.existsByEmail(id_cliente.getEmail()) || !clienteRepository.existsById(id_cliente.getId()))
            throw new ClientDoesNotExistException();
        return bigliettoRepository.sumTheTotalPriceForClient(id_cliente);
    }


   /* @Transactional(readOnly = true)
    public float priceByTicket(int id_cliente, int numeroBiglietti)  throws ClientDoesNotExistException{
        return bigliettoRepository.calculatePriceByCliente(id_cliente,numeroBiglietti);
    }*/

    @Transactional(readOnly = true)
    public Biglietto showAllSeatsById(int id_biglietto){
        return bigliettoRepository.findById(id_biglietto);
    }

   /* @Transactional(readOnly = true)
    public float getTotalPriceByTicket(int numeroBiglietti){
        return bigliettoRepository.calculateTotalPrice(numeroBiglietti);
    }*/

    @Transactional(readOnly = true)
    public List<Biglietto> showAllBySeats(Posto id_posto){
        return bigliettoRepository.findByPosto(id_posto);
    }


    @Transactional(readOnly = false)
    public List<Biglietto> deleteTicketsByCliente(Cliente id_cliente){
        List<Biglietto> deletedTickets = bigliettoRepository.findByCliente(id_cliente);
       for (Biglietto ticket : deletedTickets) {
            bigliettoRepository.delete(ticket);
        }
        return deletedTickets;
    }

    @Transactional(readOnly = false)
    public Biglietto chooseSeat(Cliente id_cliente, Posto selected_seat, Evento id_evento, String name, float price) throws TheSeatIsNotAvailableException {
        if(!selected_seat.isAvailable())
            throw new TheSeatIsNotAvailableException();

        //float price= id_evento.getSpettacolo().getPrice();

        //creo il nuovo biglietto
        Biglietto ticket= new Biglietto();
        ticket.setEvento(id_evento);
        ticket.setCliente(id_cliente);
        ticket.setPosto(selected_seat);
        ticket.setPrice(price);
        ticket.setName(name);

        //salvo il biglietto
        ticket= bigliettoRepository.save(ticket);

        //faccio in modo che il posto risulti occupato, così nessuno può sceglierlo successivamente
        selected_seat.setAvailable(false);
        postoRepository.save(selected_seat);
        return ticket;
    }

}
