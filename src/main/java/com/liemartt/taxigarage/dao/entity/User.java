package com.liemartt.taxigarage.dao.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@RequiredArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(name = "username", unique=true, nullable = false)
    private String username;
    @Column(name = "password", unique=true, nullable = false)
    private String password;

    @OneToMany(mappedBy = "user")
    private List<Car> rentedCars;
    @OneToMany(mappedBy = "author")
    private List<Review> reviews;
}
