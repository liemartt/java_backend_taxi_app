package com.liemartt.taxigarage.dto;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class ReviewRequestDto{
        private Long authorId;
        private Long carId;
        private String content;
        private int mark;
}
