package com.aston.frontendpracticeservice.controller;

import com.aston.frontendpracticeservice.domain.dto.RequisitesProjection;
import com.aston.frontendpracticeservice.service.RequisiteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/requisites")
public class RequisiteController {

    private final RequisiteService requisiteService;

    @GetMapping("/{id}")
    public ResponseEntity<RequisitesProjection> getRequisitesByUser(@PathVariable("id") Long id){
        RequisitesProjection requisitesProjection = requisiteService.getRequisites(id);
        return new ResponseEntity<>(requisitesProjection, HttpStatus.OK);
    }
}
