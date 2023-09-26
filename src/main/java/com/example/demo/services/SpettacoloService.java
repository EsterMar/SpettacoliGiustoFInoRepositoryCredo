package com.example.demo.services;

import com.example.demo.exceptions.DateWrongRangeException;
import com.example.demo.entities.Spettacolo;
import com.example.demo.repositories.SpettacoloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class SpettacoloService {

    @Autowired
    private SpettacoloRepository spettacoloRepository;




}
