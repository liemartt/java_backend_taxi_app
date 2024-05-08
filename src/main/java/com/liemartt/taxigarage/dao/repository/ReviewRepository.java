package com.liemartt.taxigarage.dao.repository;

import com.liemartt.taxigarage.dao.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findAllByCarId(Long carId);
    List<Review> findAllByAuthorId(Long authorId);
}
