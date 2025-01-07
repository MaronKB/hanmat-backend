package com.human.hanmat.dto;

import com.human.hanmat.entity.Restaurant;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class RestaurantDTO {
    private int id;
    private String name;
    private String lmmAddr;
    private String roadAddr;
    private double latitude;
    private double longitude;
    private Date regDate;
    private boolean closed;

    @Builder
    public RestaurantDTO(int id, String name, String lmmAddr, String roadAddr, double latitude, double longitude, Date regDate, boolean closed) {
        this.id = id;
        this.name = name;
        this.lmmAddr = lmmAddr;
        this.roadAddr = roadAddr;
        this.latitude = latitude;
        this.longitude = longitude;
        this.regDate = regDate;
        this.closed = closed;
    }

    public RestaurantDTO(Restaurant restaurant) {
        this.id = restaurant.getId();
        this.name = restaurant.getName();
        this.lmmAddr = restaurant.getLmmAddr();
        this.roadAddr = restaurant.getRoadAddr();
        this.latitude = restaurant.getY();
        this.longitude = restaurant.getX();
        this.regDate = restaurant.getRegDate();
        this.closed = (restaurant.getIsClosed().equals("Y"));
    }
}
