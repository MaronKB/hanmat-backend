package com.human.hanmat.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RestaurantDTO {
    private int id;
    private String name;
    private String lmmAddr;
    private String roadAddr;
    private boolean isClosed;

    @Builder
    public RestaurantDTO(int id, String name, String lmmAddr, String roadAddr, boolean isClosed) {
        this.id = id;
        this.name = name;
        this.lmmAddr = lmmAddr;
        this.roadAddr = roadAddr;
        this.isClosed = isClosed;
    }
}
