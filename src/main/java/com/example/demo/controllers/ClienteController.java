package com.example.demo.controllers;

import com.example.demo.ResponseMessage;
import com.example.demo.exceptions.MailAlreadyExistsExcpetion;
import com.example.demo.entities.Cliente;
import com.example.demo.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping("/register")
    public ResponseEntity create(@RequestBody @Valid Cliente id_cliente) {
        try {
            Cliente added = clienteService.registerCliente(id_cliente);
            return new ResponseEntity(added, HttpStatus.OK);
        } catch (MailAlreadyExistsExcpetion e) {
            return new ResponseEntity<>(new ResponseMessage("ERROR_MAIL_USER_ALREADY_EXISTS"), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/search")
    public List<Cliente> getAll() {
        return clienteService.showAllTheClient();
    }

}
