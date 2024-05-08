package com.liemartt.taxigarage.dto;

import com.liemartt.taxigarage.dao.entity.CarType;
import lombok.Builder;

@Builder
public record CarDto(Long id, String brand, String model, int year, CarType type, boolean isRented) {
}
