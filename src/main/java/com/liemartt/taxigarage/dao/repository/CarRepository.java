package com.liemartt.taxigarage.dao.repository;

import com.liemartt.taxigarage.dao.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Integer> {
}
