package com.aston.frontendpracticeservice.service;

import com.aston.frontendpracticeservice.domain.dto.UserDto;
import com.aston.frontendpracticeservice.domain.entity.User;
import com.aston.frontendpracticeservice.domain.mapper.UserMapper;
import com.aston.frontendpracticeservice.exception.UserIsFoundException;
import com.aston.frontendpracticeservice.exception.UserNotFoundException;
import com.aston.frontendpracticeservice.kafka.producer.KafkaProducer;
import com.aston.frontendpracticeservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper mapper;
    private final KafkaProducer kafkaProducer;

    public User findByLogin(String login) {
        return userRepository.findByLogin(login)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    @Transactional(readOnly = true)
    public List<UserDto> findAll() {
        List<User> allUsers = userRepository.findAll();
        if (allUsers.isEmpty()) {
            throw new UserNotFoundException("Users not found");
        }
        return allUsers
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    @Transactional(readOnly = true)
    @Cacheable(value = "UserService::getByFistAndLastName", key = "#firstName + '.' + #lastName")
    public UserDto findByFirstAndLastName(String firstName, String lastName) {
        return userRepository.findByFirstNameAndLastName(firstName, lastName)
                .map(mapper::toDto)
                .orElseThrow(() -> new UserNotFoundException(
                        "User with firstname:" + firstName + " and lastname: " + lastName + " not found"
                ));
    }

    @Transactional(readOnly = true)
    @Cacheable(value = "UserService::getById", key = "#id")
    public UserDto findById(Long id) {
        Optional<UserDto> userFromDb = userRepository.findById(id)
                .map(mapper::toDto);
        userFromDb.ifPresent(kafkaProducer::sendAsyncMessage);
        return userFromDb
                .orElseThrow(() -> new UserNotFoundException("User with id:" + id + " not found"));
    }

    @Transactional
    @CacheEvict(value = "UserService::getById", key = "#id")
    public void deleteUserById(Long id) {
        User userFromDb = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        if (Objects.nonNull(userFromDb)) {
            userRepository.delete(userFromDb);
        }
    }

    @Transactional
    @Caching(put = {
            @CachePut(value = "UserService::getById", key = "#userDto.id"),
            @CachePut(value = "UserService::getByFistAndLastName", key = "#userDto.firstName + '.' + #userDto.lastName")
    })
    public void createUser(UserDto userDto) {
        userRepository
                .findByFirstNameAndLastName(userDto.getFirstName(), userDto.getLastName())
                .ifPresentOrElse(
                        user -> {
                            throw new UserIsFoundException("User already exists");
                        },
                        () -> userRepository.save(mapper.toUser(userDto))
                );
    }
}
