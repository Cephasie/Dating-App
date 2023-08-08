package africa.semicolon.promiscuous.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "users")
@Getter
@Setter

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LocalDate dateOfBirth;

    private String firstName;

    private String lastName;

    @OneToOne
    private Address address;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Enumerated(value = EnumType.STRING)
    private Role authority;

    @Column(unique = true, nullable  = false)
    private String email;

    @Column(unique = true)
    private String phoneNumber;

    @Column(nullable  = false)
    private String password;

    private boolean isActive;


}
