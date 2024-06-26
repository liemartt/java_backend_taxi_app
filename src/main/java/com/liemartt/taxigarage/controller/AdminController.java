package com.liemartt.taxigarage.controller;

import com.liemartt.taxigarage.dao.entity.Rent;
import com.liemartt.taxigarage.dao.entity.Review;
import com.liemartt.taxigarage.dao.entity.User;
import com.liemartt.taxigarage.dto.CarDto;
import com.liemartt.taxigarage.dto.RentResponseDto;
import com.liemartt.taxigarage.dto.ReviewResponseDto;
import com.liemartt.taxigarage.mappers.CarMapper;
import com.liemartt.taxigarage.dto.UserDto;
import com.liemartt.taxigarage.mappers.RentMapper;
import com.liemartt.taxigarage.mappers.UserMapper;
import com.liemartt.taxigarage.service.CarService;
import com.liemartt.taxigarage.service.RentService;
import com.liemartt.taxigarage.service.ReviewService;
import com.liemartt.taxigarage.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {
    private final CarMapper carMapper;
    private final CarService carService;
    private final UserService userService;
    private final RentService rentService;
    private final ReviewService reviewService;
    private final RentMapper rentMapper;
    private final UserMapper userMapper;

    @GetMapping("/users")
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/user/{id}")
    public UserDto getUserById(@PathVariable Long id) {
        Optional<User> user = userService.getUserById(id);
        return userMapper.userToUserDto(user.orElse(null));
    }

    @DeleteMapping("/user/{id}")
    public void deleteUserById(@PathVariable Long id) {
        userService.deleteUserById(id);
    }

    @GetMapping("/all-rents")
    public List<RentResponseDto> getAllRents() {
        return rentService.getAllRents().stream().map(rentMapper::rentToRentResponseDto).toList();
    }
    @GetMapping("/rent/{id}")
    public RentResponseDto getAllRents(@PathVariable Long id) {
        return rentMapper.rentToRentResponseDto(rentService.getRentById(id).get());
    }
    @PostMapping("/new-car")
    public void saveCar(@RequestBody CarDto carDto){
        carService.saveCar(carDto);
    }
    @GetMapping("/reviews")
    public List<ReviewResponseDto> getAllReviews() {
        return reviewService.getAllReviews();
    }
    @DeleteMapping("/review/{id}")
    public void deleteReviewById(@PathVariable Long id) {
        reviewService.deleteReview(id);
    }
    @DeleteMapping("/car/{id}")
    public void deleteCarById(@PathVariable Long id) {
        carService.deleteCarById(id);
    }
    @PostMapping("/rent/{id}")
    public void endRentById(@PathVariable Long id) {
        rentService.endRentById(id);
    }
}
