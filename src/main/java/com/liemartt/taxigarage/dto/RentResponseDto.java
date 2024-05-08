package com.liemartt.taxigarage.dto;

import java.time.LocalDate;

public record RentResponseDto(UserDto user, CarDto car, LocalDate startDate, LocalDate endDate) {
}
