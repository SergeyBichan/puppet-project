package com.aston.frontendpracticeservice;

import com.aston.frontendpracticeservice.config.TestContainersConfig;
import com.aston.frontendpracticeservice.domain.dto.RequisitesProjection;
import com.aston.frontendpracticeservice.service.RequisiteService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RequisitesServiceTest extends TestContainersConfig {

    @Autowired
    private RequisiteService requisiteService;

    @Test
    @DisplayName("Проверка на правильность возвращаемых реквизитов из БД")
    public void shouldReturnCorrectRequisites() {
        RequisitesProjection requisites = requisiteService.getRequisites(1L);
        RequisitesProjection testRequisites = RequisitesProjection.builder()
                .accountNumber("fds")
                .firstName("Sergey")
                .lastName("Bichan")
                .kbk("fds")
                .build();

        Assertions.assertEquals(testRequisites, requisites);
    }
}
