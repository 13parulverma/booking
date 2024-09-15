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
public class UserDTO {
    @Id
    private Long userId;
    @Field
    private String userName;
    @Field
    private Long bookingId;

}
