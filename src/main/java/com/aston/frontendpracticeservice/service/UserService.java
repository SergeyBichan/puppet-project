package com.aston.frontendpracticeservice.service;

import com.aston.frontendpracticeservice.domain.dto.UserDto;
import com.aston.frontendpracticeservice.domain.entity.User;
import com.aston.frontendpracticeservice.domain.mapper.UserMapper;
import com.aston.frontendpracticeservice.exception.UserNotFoundException;
import com.aston.frontendpracticeservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper mapper;

    public User findByLogin(String login) {
        return userRepository.findByLogin(login)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    public List<UserDto> findAll() {
        List<User> allUsers = userRepository.findAll();
        if (allUsers.isEmpty()){
            throw new UserNotFoundException("Users not found");
        }
        return allUsers
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    public UserDto findByFirstAndLastName(String firstName, String lastName) {
        return userRepository.findByFirstNameAndLastName(firstName, lastName)
                .map(mapper::toDto)
                .orElseThrow(() -> new UserNotFoundException(
                        "User with firstname:" + firstName + " and lastname: " + lastName + " not found"
                ));
    }

    public UserDto findById(Long id) {
        return userRepository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new UserNotFoundException("User with id:" + id + " not found"));
    }
}
