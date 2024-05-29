package com.example.demo.services;

import com.example.demo.exceptions.ClientDoesNotExistException;
import com.example.demo.exceptions.MailAlreadyExistsExcpetion;
import com.example.demo.entities.Cliente;
import com.example.demo.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Cliente registerCliente(Cliente id_cliente) throws MailAlreadyExistsExcpetion {
        if (clienteRepository.existsByEmail(id_cliente.getEmail()))
            throw new MailAlreadyExistsExcpetion();
        //creo il nuovo cliente sulla base del cliente passato come parametro
        Cliente cl= new Cliente();
        cl.setId(id_cliente.getId());
        cl.setEmail(id_cliente.getEmail());
        cl.setAddress(id_cliente.getAddress());
        cl.setName(id_cliente.getName());
        cl.setSurname(id_cliente.getSurname());
        cl.setTelephone_number(id_cliente.getTelephone_number());
        return clienteRepository.save(cl); //sempre buono riportare ciò che è stato aggiunto al cliente
    }

    @Transactional (readOnly = true)
    public List<Cliente> showAllTheClient() {
        return clienteRepository.findAll();
    }

    @Transactional (readOnly = true)
    public Cliente showClientByEmail(String email) throws ClientDoesNotExistException {
        return clienteRepository.findByEmail(email);
    }
}
