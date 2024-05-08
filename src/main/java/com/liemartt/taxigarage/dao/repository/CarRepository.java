package com.liemartt.taxigarage.dao.repository;

import com.liemartt.taxigarage.dao.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    void deleteById(Long id);

    Car findByModel(String model);
}
