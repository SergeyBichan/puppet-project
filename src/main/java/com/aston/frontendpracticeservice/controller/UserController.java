package com.aston.frontendpracticeservice.controller;

import com.aston.frontendpracticeservice.domain.dto.UserDto;
import com.aston.frontendpracticeservice.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/users")
public class UserController {

    private final UserService service;

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") @Valid Long id) {
        UserDto byId = service.findById(id);
        return new ResponseEntity<>(byId, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserDto>> getAllUsers(){
        List<UserDto> all = service.findAll();
        return new ResponseEntity<>(all, HttpStatus.OK);
    }

    @GetMapping("/{firstName}/{lastName}")
    public ResponseEntity<UserDto> getUserByLogin(
            @PathVariable("firstName") @Valid String firstName,
            @PathVariable("lastName") @Valid String lastName) {
        UserDto dto = service.findByFirstAndLastName(firstName, lastName);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}
