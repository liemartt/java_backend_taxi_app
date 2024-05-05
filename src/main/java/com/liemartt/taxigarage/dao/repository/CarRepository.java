package com.liemartt.taxigarage.dao.repository;

import com.liemartt.taxigarage.dao.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {
    Car findById(Long id);

    void deleteById(Long id);

    Car findByModel(String model);
}
