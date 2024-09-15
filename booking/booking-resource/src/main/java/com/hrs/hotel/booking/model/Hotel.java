package com.hrs.hotel.booking.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class Hotel {
    private Long hotelId;
    private String hotelName;
    private String hotelAddress;
    private String hotelCity;
    private String hotelCountry;
    private String hotelZipCode;
    private String hotelPhoneNumber;
    private String hotelEmail;
    private List<Room> rooms = new ArrayList<>();


}
