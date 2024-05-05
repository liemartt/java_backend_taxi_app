package com.liemartt.taxigarage.dao.repository;

import com.liemartt.taxigarage.dao.entity.CarType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarTypeRepository extends JpaRepository<CarType, Long> {
}
