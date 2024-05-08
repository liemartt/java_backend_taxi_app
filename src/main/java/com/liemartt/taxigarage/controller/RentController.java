package com.liemartt.taxigarage.controller;

import com.liemartt.taxigarage.dao.entity.Rent;
import com.liemartt.taxigarage.dto.RentResponseDto;
import com.liemartt.taxigarage.dto.ReviewRequestDto;
import com.liemartt.taxigarage.service.RentService;
import com.liemartt.taxigarage.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/rents")
public class RentController {
    private final RentService rentService;
    private final ReviewService reviewService;

    @GetMapping
    public List<RentResponseDto> getRents() {
        //TODO get userID from auth
        Long userId = null;
        return rentService.getAllRentsByUserId(userId);
    }

    @PostMapping("/new-review")
    public void saveNewReview(@RequestBody ReviewRequestDto reviewRequestDto) {
        //TODO get userID from auth
        Long userId = 4l;
        reviewRequestDto.setAuthorId(userId);
        int rentCounter = rentService.countRentsByCarAndUser(reviewRequestDto.getCarId(), userId);
        if (rentCounter > 0) {
            reviewService.createReview(reviewRequestDto);
        }
    }

    @GetMapping("/current")
    public List<RentResponseDto> getCurrentRentsOfUser() {
        //TODO get userID from auth
        Long userId = null;
        return rentService.getCurrentRentsByUserId(userId);
    }

    @GetMapping("/ended")
    public List<RentResponseDto> getEndedRentsOfUser() {
        //TODO get userID from auth
        Long userId = null;
        return rentService.getEndedRentsByUserId(userId);
    }

    @GetMapping("/{rentId}/end")
    public void endRent(@PathVariable Long rentId) {
        Long userId = null;
        //TODO get userID from auth
        //TODO check if user have such rent
        rentService.endRentById(rentId);
    }
}
