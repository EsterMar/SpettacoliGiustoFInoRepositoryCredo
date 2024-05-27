package com.example.demo;

import com.example.demo.entities.Cliente;
import com.example.demo.entities.Evento;
import com.example.demo.entities.Posto;

import jakarta.validation.Valid;

public class ParamAddTicket {


    @Valid
    private Evento evento;

    @Valid
    private Integer postoId;

    @Valid
    private Cliente cliente;
    @Valid
    private float ticketPrice;


    public Evento getEvento() {
        return evento;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public int getPostoId() {return postoId;}
    public float getTicketPrice() {return ticketPrice;}

}
