package africa.semicolon.promiscuous.models;

import jakarta.persistence.*;

import java.time.LocalDate;
@Entity
@Table(name = "users")

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long id;
    private LocalDate dateOfBirth;

    @OneToOne
    private Address address;

    @Enumerated
    private Gender gender;

    @OneToOne
    private BasicData basicData;

    @Enumerated
    private Role role;


}
