package com.hrs.hotel.booking.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Setter
@Getter
public class Room {
    private Long roomId;
    private String roomNumber;
    private String roomType;
    private Double price;
    private Boolean availability;

 }
