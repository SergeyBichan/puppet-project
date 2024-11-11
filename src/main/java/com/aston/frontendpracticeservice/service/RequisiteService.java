package com.aston.frontendpracticeservice.service;

import com.aston.frontendpracticeservice.domain.dto.RequisitesProjection;
import com.aston.frontendpracticeservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@RequiredArgsConstructor
@Service
public class RequisiteService {
    private final UserRepository repository;

    public RequisitesProjection getRequisites(Long id) {
        if (Objects.isNull(id) || id == 0) {
            throw new RuntimeException("Incorrect format");
        }
        return repository.findUserWithRequisites(id);
    }
}
