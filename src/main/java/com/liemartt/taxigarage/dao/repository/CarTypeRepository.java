package com.liemartt.taxigarage.dao.repository;

import com.liemartt.taxigarage.dao.entity.CarType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarTypeRepository extends JpaRepository<CarType, Long> {
}
