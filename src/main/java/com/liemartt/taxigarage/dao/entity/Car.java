package com.liemartt.taxigarage.dao.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "cars")
@Getter
@Setter
@RequiredArgsConstructor
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "brand")
    private String brand;
    @Column(name = "model", unique = true, nullable = false)
    private String model;
    @Column(name = "year")
    private int year;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "car_type_id")
    private CarType type;
    @OneToMany(mappedBy = "car")
    private List<Review> reviews;
}
