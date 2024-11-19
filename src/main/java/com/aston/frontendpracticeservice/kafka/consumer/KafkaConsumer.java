package com.aston.frontendpracticeservice.kafka.consumer;

import com.aston.frontendpracticeservice.domain.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

import static com.aston.frontendpracticeservice.kafka.constants.KafkaConstants.MY_GROUP_ID;
import static com.aston.frontendpracticeservice.kafka.constants.KafkaConstants.MY_TOPIC;

@Component
@Slf4j
public class KafkaConsumer {

    @KafkaListener(topics = MY_TOPIC, groupId = MY_GROUP_ID)
    public CompletableFuture<UserDto> listen(UserDto userDto) {

        CompletableFuture<UserDto> future = new CompletableFuture<>();
        future.complete(userDto);
        log.info("Consumer received in async: {}", userDto);

        return future;
    }
}
