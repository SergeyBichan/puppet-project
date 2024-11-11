package com.aston.frontendpracticeservice.domain.dto;

import lombok.Builder;

@Builder
public record RequisitesProjection(String firstName, String lastName, String accountNumber, String kbk) {}
