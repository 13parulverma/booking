package com.hrs.hotel.booking.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Setter
@Getter
public class User {
    private Long userId;
    private String userName;

}
