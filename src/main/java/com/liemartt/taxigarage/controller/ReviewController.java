package com.liemartt.taxigarage.controller;

import com.liemartt.taxigarage.mappers.ReviewMapper;
import com.liemartt.taxigarage.dto.ReviewResponseDto;
import com.liemartt.taxigarage.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reviews")
public class ReviewController {
    private final ReviewService reviewService;
    private final ReviewMapper reviewMapper;

    @GetMapping
    public List<ReviewResponseDto> getAllReviews() {
        return reviewService.getAllReviews();
    }
    @GetMapping("/sort/{type}")
    //types: old, new, positive, negative
    public List<ReviewResponseDto> getFilteredReviews(@PathVariable String type) {
        return reviewService.getFilteredReviews(type);
    }
}
