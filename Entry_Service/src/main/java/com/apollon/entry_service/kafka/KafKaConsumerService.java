package com.apollon.entry_service.kafka;

import com.apollon.entry_service.repository.EntryRepository;
import com.apollon.entry_service.service.SavingEntry;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.ConcurrentKafkaListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

@Service
public class KafKaConsumerService {
    @Autowired
    SavingEntry savingEntry;

    @Autowired
    EntryRepository entryRepository;

    @KafkaListener(topics = AppConstants.TOPIC_NAME,
            groupId = AppConstants.GROUP_ID)
    public void listen(List<String> batch) {
        System.out.println(batch.size());
        Iterator iterator = batch.iterator();
        while (iterator.hasNext()) {
            JSONObject newEntry = new JSONObject(iterator.next().toString());
            savingEntry.saveEntry(newEntry);
        }
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<?, ?> kafkaListenerContainerFactory(
            ConcurrentKafkaListenerContainerFactoryConfigurer configurer,
            ConsumerFactory<Object, Object> kafkaConsumerFactory) {
        ConcurrentKafkaListenerContainerFactory<Object, Object> factory = new ConcurrentKafkaListenerContainerFactory<>();
        configurer.configure(factory, kafkaConsumerFactory);
        factory.setBatchListener(true);
        return factory;
    }
}
