package com.liemartt.taxigarage.controller;

import com.liemartt.taxigarage.dao.entity.User;
import com.liemartt.taxigarage.dto.*;
import com.liemartt.taxigarage.mappers.CarMapper;
import com.liemartt.taxigarage.service.CarService;
import com.liemartt.taxigarage.service.JwtService;
import com.liemartt.taxigarage.service.RentService;
import com.liemartt.taxigarage.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/cars")
public class CarController {
    private final CarService carService;
    private final CarMapper carMapper;
    private final ReviewService reviewService;
    private final RentService rentService;
    private final JwtService jwtService;

    @GetMapping
    public List<CarDto> getCars(){
        log.info("Get all cars");
        return carService.getAllCars().stream().map(carMapper::toDto).toList();
    }
    @GetMapping("/available")
    public List<CarDto> getAvailableCars(){
        log.info("Get available cars");
        return carService.getAvailableCars().stream().map(carMapper::toDto).toList();
    }
    @GetMapping("/{id}")
    public CarDto getCar(@PathVariable Long id){
        log.info("Get car by id: {}", id);
        return carService.getCarById(id).map(carMapper::toDto).orElse(null);
    }
    @GetMapping("/{id}/reviews")
    public List<ReviewResponseDto> getCarReviews(@PathVariable Long id){
        log.info("Get car reviews by id: {}", id);
        return reviewService.getReviewsByCarId(id);
    }
    @PostMapping("/{id}/rent")
    public void rentCar(@PathVariable Long id, @RequestParam("token") String token){
        String username = jwtService.getUsername(token);
        RentRequestDto rentCarDto = new RentRequestDto(username, id);
        log.info("Rent car");
        rentService.createRent(rentCarDto);
    }

}
