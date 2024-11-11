package com.aston.frontendpracticeservice.service;

import com.aston.frontendpracticeservice.domain.dto.RequisitesProjection;
import com.aston.frontendpracticeservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RequisiteService {
    private final UserRepository repository;

    public RequisitesProjection getRequisites(Long id) {
        return repository.findUserWithRequisites(id);
    }
}
