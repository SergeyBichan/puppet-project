package com.aston.frontendpracticeservice;

import com.aston.frontendpracticeservice.config.TestContainersConfig;
import com.aston.frontendpracticeservice.domain.dto.UserDto;
import com.aston.frontendpracticeservice.security.Role;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;

import java.time.LocalDate;
import java.util.Set;

import static com.aston.frontendpracticeservice.constants.ConstantsForTest.GROUP_FOR_CONSUMER;
import static com.aston.frontendpracticeservice.constants.ConstantsForTest.TOPIC_FOR_KAFKA;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class KafkaUserControllerTest extends TestContainersConfig {

    @Autowired
    private KafkaTemplate<String, UserDto> kafkaTemplate;

    private final UserDto userDto = UserDto.builder()
            .id(1L)
            .firstName("PADLA")
            .lastName("Bichan")
            .birthDate(LocalDate.of(1990, 7, 3))
            .inn("123461234562")
            .snils("13465443243")
            .passportNumber("dsad1231231")
            .login("admin")
            .password("admin")
            .roles(Set.of(Role.ADMIN))
            .build();

    private static UserDto receivedMessage;

    @KafkaListener(topics = TOPIC_FOR_KAFKA, groupId = GROUP_FOR_CONSUMER)
    public void listen(ConsumerRecord<String, UserDto> record) {
        receivedMessage = record.value();
    }

    @Test
    @Order(1)
    @DisplayName(value = "Тест на отправку и получение сообщение из брокера")
    public void testKafkaMessageReceiving() throws InterruptedException {
        kafkaTemplate.send(TOPIC_FOR_KAFKA, userDto);
        Thread.sleep(5000);
        Assertions.assertEquals(userDto, receivedMessage);
    }

    @Test
    @Order(2)
    public void testMessageFromKafkaShouldBeNotNull() throws InterruptedException {
//        kafkaTemplate.send(TOPIC_FOR_KAFKA, userDto);
        Thread.sleep(6000);
        Assertions.assertNotNull(receivedMessage);
    }
}