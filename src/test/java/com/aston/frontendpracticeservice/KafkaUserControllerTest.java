package com.aston.frontendpracticeservice;

import com.aston.frontendpracticeservice.config.TestContainersConfig;
import com.aston.frontendpracticeservice.domain.dto.UserDto;
import com.aston.frontendpracticeservice.security.Role;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;

import java.time.LocalDate;
import java.util.Set;

@SpringBootTest
public class KafkaUserControllerTest extends TestContainersConfig {

    @Autowired
    private KafkaTemplate<String, UserDto> kafkaTemplate;

    private UserDto receivedMessage;

    @KafkaListener(topics = "test-topic", groupId = "test-group")
    public void listen(UserDto userDto) throws InterruptedException {
        Thread.sleep(1000);
        receivedMessage = userDto;
    }

    @Test
    public void testKafkaMessageSending() throws InterruptedException {
        UserDto userDto = UserDto.builder()
                .id(1L)
                .firstName("Sergey")
                .lastName("Bichan")
                .birthDate(LocalDate.of(1990, 7, 3))
                .inn("123461234562")
                .snils("13465443243")
                .passportNumber("dsad1231231")
                .login("admin")
                .password("admin")
                .roles(Set.of(Role.ADMIN))
                .build();

        kafkaTemplate.send("test-topic", userDto);
        System.out.println("send " + userDto + " to kafka");

        Thread.sleep(5000);

        Assertions.assertEquals(userDto, receivedMessage);
    }
}
