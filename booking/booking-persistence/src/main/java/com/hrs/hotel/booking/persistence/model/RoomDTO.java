package com.hrs.hotel.booking.persistence.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.Field;
import org.springframework.stereotype.Component;

@Document
@Setter
@Getter
public class RoomDTO {
    @Id
    private Long roomId;
    @Field
    private String roomNumber;
    @Field
    private String roomType;
    @Field
    private Double price;
    @Field
    private Boolean availability;
}
