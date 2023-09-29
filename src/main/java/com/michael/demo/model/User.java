package com.michael.demo.model;
import jakarta.persistence.*;
import lombok.*;


import java.io.Serializable;
import java.time.LocalDate;


@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Entity
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fullName;
    @Column(unique = true)
    private String email;
    private LocalDate dateOfBirth;
    private String password;
    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

}
