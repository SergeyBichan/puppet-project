package com.aston.frontendpracticeservice.domain.response;

import lombok.Builder;

import java.util.Map;

@Builder
public record NotSimpleMessage (
        String httpCode,
        String message,
        Map<String, String> details) {}
