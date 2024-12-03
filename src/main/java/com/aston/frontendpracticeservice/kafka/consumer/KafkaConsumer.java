package com.aston.frontendpracticeservice.kafka.consumer;

import com.aston.frontendpracticeservice.domain.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;


@Component
@Slf4j
public class KafkaConsumer {
    @KafkaListener(topics = "${spring.kafka.topic}", groupId = "${spring.kafka.group-id}")
    public CompletableFuture<UserDto> listen(UserDto userDto, Acknowledgment acknowledgment) {

        CompletableFuture<UserDto> future = new CompletableFuture<>();
        future.complete(userDto);
        log.info("Consumer received in async: {}", userDto);
        try {
            acknowledgment.acknowledge();
            log.info("Сообщение {} обработано!", userDto);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return future;
    }
}
