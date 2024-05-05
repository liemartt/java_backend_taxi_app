package com.liemartt.taxigarage.dao.repository;

import com.liemartt.taxigarage.dao.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
