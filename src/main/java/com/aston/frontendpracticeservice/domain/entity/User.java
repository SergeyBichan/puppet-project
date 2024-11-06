package com.aston.frontendpracticeservice.domain.entity;

import com.aston.frontendpracticeservice.security.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
@NamedEntityGraph(name = "roles",
        attributeNodes = @NamedAttributeNode("roles"))
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "first_name")
    private String firstName;

    @Column(nullable = false, name = "last_name")
    private String lastName;

    @Column(nullable = false, name = "birth_date")
    private LocalDate birthDate;

    @Column(nullable = false, name = "inn")
    private String inn;

    @Column(nullable = false, name = "snils")
    private String snils;

    @Column(nullable = false, name = "passport_number")
    private String passportNumber;

    @Column(nullable = false, name = "login")
    private String login;

    @Column(nullable = false, name = "password")
    private String password;

    @ElementCollection(fetch = FetchType.LAZY)
    @Fetch(FetchMode.JOIN)
    @Enumerated(EnumType.STRING)
    @Column(name = "user_role")
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))

    private Set<Role> roles;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(firstName, user.firstName)
                && Objects.equals(lastName, user.lastName)
                && Objects.equals(birthDate, user.birthDate)
                && Objects.equals(inn, user.inn)
                && Objects.equals(snils, user.snils)
                && Objects.equals(passportNumber, user.passportNumber)
                && Objects.equals(login, user.login)
                && Objects.equals(password, user.password)
                && Objects.equals(roles, user.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id,
                firstName,
                lastName,
                birthDate,
                inn,
                snils,
                passportNumber,
                login,
                password,
                roles);
    }
}
