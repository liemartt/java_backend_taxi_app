package com.liemartt.taxigarage.dao.repository;

import com.liemartt.taxigarage.dao.entity.Rent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RentRepository extends JpaRepository<Rent, Long> {
    List<Rent> findAllByUserId(Long userId);
    List<Rent> findAllByUserUsername(String username);
    List<Rent> findAllByCarId(Long carId);
    int countRentsByCarIdAndUserId(Long carId, Long userId);
}
