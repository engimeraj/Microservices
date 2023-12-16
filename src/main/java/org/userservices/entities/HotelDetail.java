package org.userservices.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class HotelDetail {
    private String hotelId;
    private String hotelName;
    private String location;
    private String about;
}
