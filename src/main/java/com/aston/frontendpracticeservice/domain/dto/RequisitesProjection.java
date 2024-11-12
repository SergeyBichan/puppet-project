package com.aston.frontendpracticeservice.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;

@Builder
public record RequisitesProjection(
        @NotBlank(message = "First name can't be blank")
        String firstName,
        @NotBlank(message = "Last name can't be blank")
        String lastName,
        @Pattern(regexp = "^.{20}$")
        @NotBlank(message = "Номер счета не может быть пустым")
        String accountNumber,
        @NotBlank(message = "KBK не может быть пустым")
        String kbk) {}
