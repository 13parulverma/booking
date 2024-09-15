package com.hrs.hotel.booking.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Setter
@Getter
public class DeleteResponse {
    private String message;
    public DeleteResponse(String message) {
        this.message = message;
    }
    public DeleteResponse(){

    }

}
