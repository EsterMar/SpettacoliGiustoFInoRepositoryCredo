package com.example.demo.configurations;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.nio.charset.StandardCharsets;


@Configuration
public class EncodingConfiguration {


    @Bean
    //utile per la conversione di dati JSON ricevuti da HTTP in dati Java, in modo da poterci lavorare (deserializzazionee) o
    //il contrario, partire da dati Java e convertirli in dati JSON (serializzazione) come risposta da un sito web. Viene utilizzato
    //in particolare per applicazioni con Spring framework. Classe molto utilizzata in Spring MVC. Spring MVC permette di definire
    //controller che gestiscono le richieste HTTP, e MappingJackson2HttpMessageConverter è uno dei mezzi per rendere
    //possibile la comunicazione tra oggetti Java e dati JSON nelle richieste e nelle risposte HTTP.
    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
        MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
        jsonConverter.setDefaultCharset(StandardCharsets.UTF_8);
        return jsonConverter;
    }


}
