package com.liemartt.taxigarage.dto;

import java.time.LocalDate;

public record RentResponseDto(Long id, UserDto user, CarDto car, LocalDate startDate, LocalDate endDate, Boolean hasReview) {
}
