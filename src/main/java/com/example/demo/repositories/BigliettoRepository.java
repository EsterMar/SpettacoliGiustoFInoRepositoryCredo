package com.example.demo.repositories;


import com.example.demo.entities.Biglietto;
import com.example.demo.entities.Cliente;
import com.example.demo.entities.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface BigliettoRepository extends JpaRepository<Biglietto, Integer> {

    List<Biglietto> findByPrice (float price);
    List<Biglietto> findByCliente (Cliente id_cliente);

    @Query("SELECT SUM(b.price) FROM Biglietto b WHERE b.cliente= ?1")
    float sumTheTotalPriceForClient(Cliente id_cliente);

    @Query("SELECT COUNT(b) FROM Biglietto b JOIN b.evento e WHERE e.data = ?1")
    int countInDate(Date date1); //conto quanti biglietti ho venduto in quel giorno
    int countByEvento (Evento id_evento); //conto quanti biglietti ho venduto in quell'evento

    int countByCliente(Cliente id_cliente); //conto quanti biglietti ho venduto per quel client-> utile per creare i biglietti
}
