package com.example.demo.services;

import com.example.demo.entities.Evento;
import com.example.demo.entities.Posto;
import com.example.demo.entities.Spettacolo;
import com.example.demo.exceptions.DateWrongRangeException;
import com.example.demo.repositories.EventoRepository;
import com.example.demo.repositories.PostoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class EventoService {


    @Autowired
    private EventoRepository eventoRepository;
  //  @Autowired
    //private SalaRepository salaRepository;


    @Transactional(readOnly = true)
    public List<Evento> showAllEvents(int pageNumber, int pageSize, String sortBy) {
        Pageable paging = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
        Page<Evento> pagedResult = eventoRepository.findAll(paging);
        if ( pagedResult.hasContent() ) {
            return pagedResult.getContent();
        }
        else {
            return new ArrayList<>();
        }
    }

    @Transactional(readOnly = true)
    public List<Evento> showAllEvents(){
        return eventoRepository.findAll();
    }
    @Transactional(readOnly = true)
    public List<Evento> showAllEventsByShow(String title){
        return eventoRepository.findAllByIdSpettacolo(title);
    }

    @Transactional(readOnly = true)
    public List<Evento> showAllEventsBySeat(Posto id_posto){
        return eventoRepository.findAllByPosto(id_posto);
    }

    @Transactional(readOnly = true)
    public Evento showAllEventsByIdEvents(int id_evento){
        return eventoRepository.findAllById(id_evento);
    }

    @Transactional(readOnly = true)
    public boolean isAvailable(int id_evento) {
        return eventoRepository.existsEventInRoomWithAvailableSeats(id_evento);
    }

    @Transactional(readOnly = true)
    public List<Evento> showEventsInPeriod(Date start, Date end) throws DateWrongRangeException {
        if (start.compareTo(end) >= 0)
            throw new DateWrongRangeException();
        return eventoRepository.findByDataBetween(start, end);
    }

   /* @Transactional(readOnly = true)
    public boolean isAvailable(Evento id_evento) throws EventNotFoundException {
        List<Sala> rooms= salaRepository.findByEvento(id_evento);
        if(rooms.isEmpty())
            throw new EventNotFoundException();
        for(Sala s: rooms)
            return s.getCapacity()>postoRepository.countOccupiedSeats(s);
        return false;
    }*/



}