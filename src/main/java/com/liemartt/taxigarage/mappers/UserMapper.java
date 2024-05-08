package com.liemartt.taxigarage.mappers;

import com.liemartt.taxigarage.dao.entity.User;
import com.liemartt.taxigarage.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface UserMapper {
    UserDto userToUserDto(User user);
    @Mapping(target = "reviews", ignore = true)
    @Mapping(target = "rents", ignore = true)
    @Mapping(target = "password", ignore = true)
    User userDtoToUser(UserDto userDto);
}
