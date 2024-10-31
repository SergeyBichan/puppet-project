package com.aston.frontendpracticeservice.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class UserDto {

    private Long id;
    @NotBlank(message = "First name can't be blank")
    private String firstName;
    @NotBlank(message = "Last name can't be blank")
    private String lastName;
    @NotNull(message = "Дата не может быть пустой")
    @Past(message = "Неверная дата")
    private LocalDate birthDate;
    @NotBlank(message = "ИНН не может быть пустым")
    @Pattern(regexp = "\\{12}", message = "ИНН должен содержать не менее 12 цифр")
    private String inn;
    @NotBlank(message = "СНИЛС не может быть пустым")
    @Pattern(regexp = "\\{11}", message = "СНИЛС должен содержать не менее 11 цифр")
    private String snils;
    @NotBlank(message = "Номер паспорта не может быть пустым")
    @Size(min = 8, max = 20, message = "Номер паспорта должен содержать от 8 до 20 символов")
    private String passportNumber;
    @NotBlank(message = "Логин не может быть пустым")
    @Size(min = 3, max = 50, message = "Логин должен содержать от 3 до 50 символов")
    private String login;
    @NotBlank(message = "Пароль не может быть пустым")
    @Size(min = 8, max = 128, message = "Пароль должен содержать от 8 до 128 символов")
    private String password;
}
