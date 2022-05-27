package com.apollon.entry_service.service;

import com.apollon.entry_service.model.Entry;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;



@Service
public class SendingEntry {

    public static final String MICROSERVICE_URL = "http://192.168.50.13:8080/entries";

    /**
     * This method sends review to Review Stastistics microservice
     * @param savedEntry
     * @return
     */
    public ResponseEntity<Entry> sendEntry(Entry savedEntry) {

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();

        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        HttpEntity<Entry> entity = new HttpEntity<Entry>(savedEntry, headers);

        ResponseEntity<Entry> response = restTemplate.exchange(
                MICROSERVICE_URL, HttpMethod.POST, entity, Entry.class);

        return response;
    }
}
