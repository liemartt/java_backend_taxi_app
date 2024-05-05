package com.liemartt.taxigarage.dto;

import com.liemartt.taxigarage.dao.entity.Car;
import org.mapstruct.Mapper;

@Mapper
public interface CarMapper {
    CarDto toDto(Car car);
    Car toEntity(CarDto carDto);
}
