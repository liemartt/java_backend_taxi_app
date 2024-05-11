package com.liemartt.taxigarage.service;

import com.liemartt.taxigarage.dao.entity.Car;
import com.liemartt.taxigarage.dao.entity.Rent;
import com.liemartt.taxigarage.dao.entity.Review;
import com.liemartt.taxigarage.dao.entity.User;
import com.liemartt.taxigarage.dao.repository.CarRepository;
import com.liemartt.taxigarage.dao.repository.ReviewRepository;
import com.liemartt.taxigarage.dao.repository.UserRepository;
import com.liemartt.taxigarage.mappers.ReviewMapper;
import com.liemartt.taxigarage.dto.ReviewRequestDto;
import com.liemartt.taxigarage.dto.ReviewResponseDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final CarRepository carRepository;
    private final UserRepository userRepository;
    private final ReviewMapper reviewMapper;

    public List<ReviewResponseDto> getAllReviews() {
        return reviewRepository.findAll().stream().map(reviewMapper::toResponseDto).toList();
    }

    @Transactional
    public void createReview(Rent rent, ReviewRequestDto reviewRequestDto) {
        Review review = new Review();
        Car car = rent.getCar();
        User user = rent.getUser();
        review.setCar(car);
        review.setAuthor(user);
        review.setMark(reviewRequestDto.getMark());
        review.setContent(reviewRequestDto.getContent());
        reviewRepository.save(review);
    }

    public void deleteReview(Long id) {
        reviewRepository.deleteById(id);
    }

    public List<ReviewResponseDto> getReviewsByCarId(Long id) {
        return reviewRepository.findAllByCarId(id).stream().map(reviewMapper::toResponseDto).toList();
    }

    public List<ReviewResponseDto> getReviewsByAuthorId(Long id) {
        return reviewRepository.findAllByAuthorId(id).stream().map(reviewMapper::toResponseDto).toList();
    }

    public List<ReviewResponseDto> getFilteredReviews(String type) {
        switch (type) {
            case "new" -> {
                return reviewRepository
                        .findAll(Sort.by(Sort.Direction.DESC, "date"))
                        .stream()
                        .map(reviewMapper::toResponseDto)
                        .toList();
            }
            case "old" -> {
                return reviewRepository
                        .findAll(Sort.by(Sort.Direction.ASC, "date"))
                        .stream()
                        .map(reviewMapper::toResponseDto)
                        .toList();
            }
            case "positive" -> {
                return reviewRepository
                        .findAll(Sort.by(Sort.Direction.DESC, "mark"))
                        .stream()
                        .map(reviewMapper::toResponseDto)
                        .toList();
            }
            case "negative" -> {
                return reviewRepository
                        .findAll(Sort.by(Sort.Direction.ASC, "mark"))
                        .stream()
                        .map(reviewMapper::toResponseDto)
                        .toList();
            }
        }
        return reviewRepository.findAll().stream().map(reviewMapper::toResponseDto).toList();
    }
}
