package com.hrs.hotel.booking.persistence.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.Field;

import java.util.ArrayList;
import java.util.List;

@Document
@Setter
@Getter
public class HotelDTO {
    @Id
    private Long hotelId;
    @Field
    private String hotelName;
    @Field
    private String hotelAddress;
    @Field
    private String hotelCity;
    @Field
    private String hotelCountry;
    @Field
    private String hotelZipCode;
    @Field
    private String hotelPhoneNumber;
    @Field
    private String hotelEmail;
    @Field
    private List<RoomDTO> rooms = new ArrayList<>();


}
