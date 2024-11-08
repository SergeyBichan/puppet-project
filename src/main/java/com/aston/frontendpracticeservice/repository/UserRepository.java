package com.aston.frontendpracticeservice.repository;

import com.aston.frontendpracticeservice.domain.entity.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


/**
 * Mock repository
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByLogin(String login);
    @EntityGraph(value = "roles", type = EntityGraph.EntityGraphType.LOAD)
    Optional<User> findByFirstNameAndLastName(String firstName, String lastName);

    @EntityGraph(value = "roles", type = EntityGraph.EntityGraphType.LOAD)
    List<User> findAll();
}
