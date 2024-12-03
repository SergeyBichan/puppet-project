package com.aston.frontendpracticeservice.kafka.producer;

import com.aston.frontendpracticeservice.domain.dto.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaProducer {
    private final KafkaTemplate<String, UserDto> kafkaTemplate;

    @Value("${spring.kafka.topic}")
    private String topic;

    public void sendAsyncMessage(UserDto event) {
        CompletableFuture<SendResult<String, UserDto>> future = kafkaTemplate.send(topic, event);
        future.whenComplete((result, ex) -> {
            if (ex != null) {
                log.error("Error while sending message to topic {}", topic, ex);
            } else {
                log.info("Message sent in async mode to topic {}", topic);
            }
        });
    }

}
