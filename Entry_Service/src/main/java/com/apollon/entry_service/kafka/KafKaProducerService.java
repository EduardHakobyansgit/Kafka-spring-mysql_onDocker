package com.apollon.entry_service.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafKaProducerService {
    private static final Logger logger =
            LoggerFactory.getLogger(KafKaProducerService.class);

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String entry) {
        try {

            logger.info(String.format("Message sent -> %s", "New message"));
            this.kafkaTemplate.send(AppConstants.TOPIC_NAME, entry);
        } catch (Exception e) {
            System.out.println(e);
        }

    }
}
