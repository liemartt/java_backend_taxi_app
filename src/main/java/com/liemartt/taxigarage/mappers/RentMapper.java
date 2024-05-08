package com.liemartt.taxigarage.mappers;

import com.liemartt.taxigarage.dao.entity.Rent;
import com.liemartt.taxigarage.dto.CarDto;
import com.liemartt.taxigarage.dto.RentResponseDto;
import com.liemartt.taxigarage.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RentMapper {
    UserMapper USER_MAPPER = Mappers.getMapper(UserMapper.class);
    CarMapper CAR_MAPPER = Mappers.getMapper(CarMapper.class);
    default RentResponseDto rentToRentResponseDto(Rent rent){
        CarDto carDto = CAR_MAPPER.toDto(rent.getCar());
        UserDto userDto = USER_MAPPER.userToUserDto(rent.getUser());
        return new RentResponseDto(userDto, carDto, rent.getStartDate(), rent.getEndDate());
    }
}
