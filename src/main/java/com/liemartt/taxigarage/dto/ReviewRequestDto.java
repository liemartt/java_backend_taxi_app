package com.liemartt.taxigarage.dto;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class ReviewRequestDto{
        private Long rentId;
        private String content;
        private int mark;
}
