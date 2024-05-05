package com.liemartt.taxigarage.dto;

import com.liemartt.taxigarage.dao.entity.CarType;
import lombok.Builder;

@Builder
public record CarDto(String brand, String model, int year, CarType type) {
}
