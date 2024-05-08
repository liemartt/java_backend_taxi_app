package com.liemartt.taxigarage.mappers;

import com.liemartt.taxigarage.dao.entity.Car;
import com.liemartt.taxigarage.dto.CarDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface CarMapper {
    @Mapping(target = "isRented", source = "rented")
    CarDto toDto(Car car);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "reviews", ignore = true)
    @Mapping(target = "rents", ignore = true)
    @Mapping(target = "rented", ignore = true)
    Car toEntity(CarDto carDto);
}
