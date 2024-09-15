package com.hrs.hotel.booking.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Setter
@Getter
public class Booking {
    private Long bookingId;
    private String userId;
    private String userName;
    private String hotelName;
    private String checkInDate;
    private String checkOutDate;
    private Long payment;
    private List<Room> room = new ArrayList<>();

}
