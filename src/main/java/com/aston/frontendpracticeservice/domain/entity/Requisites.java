package com.aston.frontendpracticeservice.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.OneToOne;
import jakarta.persistence.JoinColumn;
import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "requisites")
public class Requisites {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "account_number", nullable = false)
    private String accountNumber;

    @Column(name = "correspondent_account_number", nullable = false)
    private String correspondentAccountNumber;

    @Column(name = "inn", nullable = false)
    private String inn;

    @Column(name = "kpp", nullable = false)
    private String kpp;

    @Column(name = "kbk", nullable = false)
    private String kbk;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}