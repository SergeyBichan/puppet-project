package com.aston.frontendpracticeservice.controller;

import com.aston.frontendpracticeservice.domain.dto.UserDto;
import com.aston.frontendpracticeservice.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/users")
public class UserController {

    private final UserService service;

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") @Valid Long id) {
        UserDto dto = service.findById(id);
        log.info("Get request getUserById - start: {}", dto);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserDto>> getAllUsers(){
        List<UserDto> all = service.findAll();
        log.info("Get request getAllUsers - start: {}", all);
        return new ResponseEntity<>(all, HttpStatus.OK);
    }

    @GetMapping("/by-name")
    public ResponseEntity<UserDto> getUserByFirstAndLastName(
            @RequestParam("firstName") @Valid String firstName,
            @RequestParam("lastName") @Valid String lastName) {
        UserDto dto = service.findByFirstAndLastName(firstName, lastName);
        log.info("Get request getUserByFirstAndLastName - start: {}", dto);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}
