package com.liemartt.taxigarage.controller;

import com.liemartt.taxigarage.dao.entity.Rent;
import com.liemartt.taxigarage.dao.entity.User;
import com.liemartt.taxigarage.dto.RentResponseDto;
import com.liemartt.taxigarage.dto.ReviewRequestDto;
import com.liemartt.taxigarage.service.JwtService;
import com.liemartt.taxigarage.service.RentService;
import com.liemartt.taxigarage.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/rents")
public class RentController {
    private final RentService rentService;
    private final ReviewService reviewService;
    private final JwtService jwtService;

    @GetMapping
    public List<RentResponseDto> getRents(@RequestParam("token") String token) {
        String username = jwtService.getUsername(token);
        return rentService.getAllRentsByUsername(username);
    }

    @PostMapping("/new-review")
    public void saveNewReview(@RequestBody ReviewRequestDto reviewRequestDto, @RequestParam("token") String token) {
        String username = jwtService.getUsername(token);
        Optional<Rent> rent = rentService.getRentById(reviewRequestDto.getRentId());
        if(rent.isPresent()&&!rent.get().getHasReview()&&rent.get().getEndDate()!=null) {
            rent.get().setHasReview(true);
            reviewService.createReview(rent.get(), reviewRequestDto);
        }
    }

    @GetMapping("/current")
    public List<RentResponseDto> getCurrentRentsOfUser(@RequestParam("token") String token) {
        String username = jwtService.getUsername(token);
        return rentService.getCurrentRentsByUsername(username);
    }

    @GetMapping("/ended")
    public List<RentResponseDto> getEndedRentsOfUser(@RequestParam("token") String token) {
        String username = jwtService.getUsername(token);
        return rentService.getEndedRentsByUsername(username);
    }

    @PostMapping("/{rentId}/end")
    public void endRent(@PathVariable Long rentId, @RequestParam("token") String token) {
        String username = jwtService.getUsername(token);
        Optional<Rent> rentOptional = rentService.getRentById(rentId);
        if (rentOptional.isPresent()) {
            Rent rent = rentOptional.get();
            if (rent.getUser().getUsername().equals(username)) {
                rentService.endRentById(rentId);
            }
        }
    }
}
