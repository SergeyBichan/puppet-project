package com.aston.frontendpracticeservice.kafka.consumer;

import com.aston.frontendpracticeservice.domain.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;


@Component
@Slf4j
public class KafkaConsumer {
    @KafkaListener(topics = "${spring.kafka.topic}", groupId = "${spring.kafka.group-id}")
    public CompletableFuture<UserDto> listen(UserDto userDto) {

        CompletableFuture<UserDto> future = new CompletableFuture<>();
        future.complete(userDto);
        log.info("Consumer received in async: {}", userDto);

        return future;
    }
}
