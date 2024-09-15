package com.hrs.hotel.booking.persistence.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.Field;

import java.util.ArrayList;
import java.util.List;

@Document
@Data
@Setter
@Getter
public class BookingDTO {
    public BookingDTO(Long id){
        this.bookingId = id;
    }
    
    public BookingDTO(){
    }
    @Id
    @Field
    private Long bookingId;
    @Field
    private String userId;
    @Field
    private String userName;
    @Field
    private String hotelId;
    @Field
    private String hotelName;
    @Field
    private String checkInDate;
    @Field
    private String checkOutDate;
    @Field
    private Long payment;
    @Field
    private List<RoomDTO> room = new ArrayList<>();

}
