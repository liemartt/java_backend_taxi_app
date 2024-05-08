package com.liemartt.taxigarage.dto;

import com.liemartt.taxigarage.dao.entity.Car;
import com.liemartt.taxigarage.dao.entity.User;

import java.time.LocalDate;

public record ReviewResponseDto(Long id, UserDto author, CarDto car, String content, int mark, LocalDate date) {
}
