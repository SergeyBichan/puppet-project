package com.aston.frontendpracticeservice.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotNull(message = "Дата не может быть пустой")
    private LocalDate birthDate;
    @NotBlank
    private String inn;
    @NotBlank
    private String snils;
    @NotBlank
    private String passportNumber;
    @NotBlank
    private String login;
    @NotBlank
    @Size(min = 8, max = 15)
    private String password;
}
