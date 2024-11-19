package com.aston.frontendpracticeservice.kafka.producer;

import com.aston.frontendpracticeservice.domain.dto.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

import static com.aston.frontendpracticeservice.kafka.constants.KafkaConstants.MY_TOPIC;


@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaProducer {

    private final KafkaTemplate<String, UserDto> kafkaTemplate;

    public void sendAsyncMessage(UserDto event) {
        CompletableFuture<SendResult<String, UserDto>> future = kafkaTemplate.send(MY_TOPIC, event);
        future.whenComplete((result, ex) -> {
            if (ex != null) {
                log.error("Error while sending message to topic {}", MY_TOPIC, ex);
            } else {
                log.info("Message sent in async mode to topic {}", MY_TOPIC);
            }
        });
    }

}
