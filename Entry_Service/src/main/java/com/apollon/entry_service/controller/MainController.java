package com.apollon.entry_service.controller;

import com.apollon.entry_service.kafka.KafKaProducerService;
import com.apollon.entry_service.service.SavingEntry;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

@RestController
public class MainController {
    @Autowired
    KafKaProducerService producerService;
    @Autowired
    SavingEntry savingEntry;


    @GetMapping("/get")
    public ResponseEntity<?> getData() {
        try {
            URL url = new URL("https://api.publicapis.org/entries");
            HttpURLConnection request = (HttpURLConnection)url.openConnection();
            request.setRequestMethod("GET");
            request.connect();
            if(request.getResponseCode() == 200) {
                Scanner scan = new Scanner(url.openStream());
                while(scan.hasNext()) {
                    String temp = scan.nextLine();
                    JSONObject obj = new JSONObject(temp);

                    JSONArray array = obj.getJSONArray("entries");
                    for (int i=0; i<array.length(); i++) {
                        this.producerService.sendMessage(array.getJSONObject(i).toString());

                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return ResponseEntity.status(HttpStatus.OK).body("The operation is successfully done");
    }

}
