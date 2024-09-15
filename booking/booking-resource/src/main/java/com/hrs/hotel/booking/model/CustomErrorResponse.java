package com.hrs.hotel.booking.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Setter
@Getter
@Component
public class CustomErrorResponse {
    private String message;
    public CustomErrorResponse(String message){
        this.message = message;
    }

}
