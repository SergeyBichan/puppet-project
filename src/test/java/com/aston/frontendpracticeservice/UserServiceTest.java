package com.aston.frontendpracticeservice;

import com.aston.frontendpracticeservice.config.TestContainersConfig;
import com.aston.frontendpracticeservice.domain.dto.UserDto;
import com.aston.frontendpracticeservice.exception.UserNotFoundException;
import com.aston.frontendpracticeservice.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest
public class UserServiceTest extends TestContainersConfig {

    @Autowired
    private UserService service;
    @Mock
   private UserService userService1;
    UserDto testUser = UserDto.builder()
            .id(1L)
            .firstName("Sergey")
            .lastName("Bichan")
            .birthDate(LocalDate.of(1990, 7, 3))
            .inn("123461234562")
            .snils("13465443243")
            .passportNumber("dsad1231231")
            .login("admin")
            .password("admin")
            .build();
    @Autowired
    private UserService userService;

    @Test
    @DisplayName("Тест для проверки существующего пользователя в БД")
    public void shouldReturnCorrectUserFromDb() {

        UserDto fromDb = service.findById(1L);

        Assertions.assertEquals(testUser, fromDb);
    }

    @Test
    @DisplayName("Проверка на наличие несуществующего пользователя в БД")
    public void shouldReturnUserNotFoundException() {
        Exception exception = Assertions.assertThrows(UserNotFoundException.class, () -> service.findById(2L));

        String expectedMessage = "User with id:" + 2L + " not found";
        String actualMessage = exception.getMessage();

        Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    @DisplayName("Проверка списка юзеров")
    public void shouldReturnCorrectListOfUsers() {
        List<UserDto> userDtoList = List.of(testUser);

        List<UserDto> all = service.findAll();

        Assertions.assertEquals(userDtoList, all);
    }


    /**
     * Как бы и не интеграционный получается, потому что данные у меня заполняют тестовую бд в контейнере с помощью ликвибейза
     */
    @Test
    @DisplayName("Проверка на пустой список пользователей")
    public void shouldReturnEmptyList() {

        when(userService1.findAll()).thenThrow(new UserNotFoundException("User not found"));

        Exception exception = Assertions.assertThrows(UserNotFoundException.class, () -> userService1.findAll());

        String expectedMessage = "User not found";
        String actualMessage = exception.getMessage();

        Assertions.assertTrue(expectedMessage.contains(actualMessage));

    }


}
