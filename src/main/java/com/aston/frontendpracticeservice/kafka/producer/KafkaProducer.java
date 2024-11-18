package com.aston.frontendpracticeservice.kafka.producer;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaProducer {

    public static final String TOPIC = "test_topic";

    private final KafkaTemplate<String, MyEvent> kafkaTemplate;

    public void sendMessage(MyEvent event) {
        String key = event.getKey();
        kafkaTemplate.send(TOPIC, key, event);
       log.info("Message sent to topic {}", TOPIC);
    }

    @Data
    class MyEvent {
        private String key;
    }
}
