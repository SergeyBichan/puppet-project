package com.aston.frontendpracticeservice.service;

import com.aston.frontendpracticeservice.domain.dto.RequisitesProjection;
import com.aston.frontendpracticeservice.exception.UserNotFoundException;
import com.aston.frontendpracticeservice.repository.RequisitesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@RequiredArgsConstructor
@Service
public class RequisiteService {
    private final RequisitesRepository repository;

    public RequisitesProjection getRequisites(Long id) {
        if (Objects.isNull(id) || id == 0) {
            throw new IllegalArgumentException("Incorrect format");
        }
        return repository.findUserWithRequisites(id).orElseThrow(
                () -> new UserNotFoundException("User with id:" + id + " not found!")
        );
    }
}
