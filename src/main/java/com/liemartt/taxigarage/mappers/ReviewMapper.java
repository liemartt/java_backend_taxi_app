package com.liemartt.taxigarage.mappers;

import com.liemartt.taxigarage.dao.entity.Review;
import com.liemartt.taxigarage.dto.ReviewResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ReviewMapper {
    UserMapper USER_MAPPER = Mappers.getMapper(UserMapper.class);
    CarMapper CAR_MAPPER = Mappers.getMapper(CarMapper.class);

    default ReviewResponseDto toResponseDto(Review review) {
        ReviewResponseDto reviewResponseDto = new ReviewResponseDto(review.getId(),
                USER_MAPPER.userToUserDto(review.getAuthor()),
                CAR_MAPPER.toDto(review.getCar()),
                review.getContent(),
                review.getMark(),
                review.getDate());
        return reviewResponseDto;
    }
}
