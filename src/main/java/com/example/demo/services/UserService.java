package com.example.demo.services;

import com.example.demo.exceptions.MailAlreadyExistsExcpetion;
import com.example.demo.entities.Cliente;
import com.example.demo.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Cliente registerCliente(Cliente id_cliente) throws MailAlreadyExistsExcpetion {
        if (clienteRepository.existsByEmail(id_cliente.getEmail()))
            throw new MailAlreadyExistsExcpetion();
        return clienteRepository.save(id_cliente); //sempre buono riportare ciò che è stato aggiunto al cliente
    }

    @Transactional (readOnly = true)
    public List<Cliente> showAllTheClient() {
        return clienteRepository.findAll();
    }
}
