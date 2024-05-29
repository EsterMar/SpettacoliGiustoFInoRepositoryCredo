package com.example.demo;

import com.example.demo.entities.Cliente;
import com.example.demo.entities.Evento;
import com.example.demo.entities.Posto;

import com.example.demo.entities.Spettacolo;
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

    @Valid
    private String clientName;



    public Evento getEvento() {
        return evento;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public int getPostoId() {return postoId;}

    public float getTicketPrice(){
        ticketPrice= evento.getSpettacolo().getPrice();
        return ticketPrice;
    }
    public String getClientName() {
        //clientName= cliente.getName()+" "+cliente.getSurname();
        return clientName;
    }
   // public float getTicketPrice() {return ticketPrice;}

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public void setPostoId(Integer postoId) {
        this.postoId = postoId;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setTicketPrice(float ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }
}
