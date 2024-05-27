package com.example.demo.repositories;


import com.example.demo.entities.Biglietto;
import com.example.demo.entities.Cliente;
import com.example.demo.entities.Evento;
import com.example.demo.entities.Posto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface BigliettoRepository extends JpaRepository<Biglietto, Integer> {

    //dovrebbe essere tutto corretto


    List<Biglietto> findByPosto (Posto id_posto);
    List<Biglietto> findByCliente (Cliente id_cliente);


    @Query("SELECT SUM(b.price) FROM Biglietto b WHERE b.cliente = ?1")
    float sumTheTotalPriceForClient(Cliente id_cliente);

    @Query("SELECT COUNT(b) FROM Biglietto b JOIN b.evento e WHERE e.data = ?1")
    int countInDate(Date date); //conto quanti biglietti ho venduto in quel giorno






    Biglietto findById(int biglietto);

}
