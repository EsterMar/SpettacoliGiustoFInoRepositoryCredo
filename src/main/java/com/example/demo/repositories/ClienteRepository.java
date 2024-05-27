package com.example.demo.repositories;

import com.example.demo.entities.Cliente;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    //è quasi sicuramente tutto corretto


    List<Cliente> findByName (String first_name);
    List<Cliente> findBySurname (String last_name);
    List<Cliente> findByNameAndSurname (String first_name, String last_name);
    List<Cliente> findByEmail (String email);
    boolean existsByEmail (String email);
}
