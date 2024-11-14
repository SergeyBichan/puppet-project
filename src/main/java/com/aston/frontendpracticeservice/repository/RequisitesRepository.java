package com.aston.frontendpracticeservice.repository;

import com.aston.frontendpracticeservice.domain.dto.RequisitesProjection;
import com.aston.frontendpracticeservice.domain.entity.Requisites;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RequisitesRepository extends JpaRepository<Requisites, Long> {

    @Query("""
            SELECT new com.aston.frontendpracticeservice.domain.dto.RequisitesProjection(
            u.firstName,
            u.lastName,
            r.accountNumber,
            r.kbk
            ) FROM Requisites r
            JOIN r.user u
            WHERE u.id=:id
            """)
    Optional<RequisitesProjection> findUserWithRequisites(Long id);
}
