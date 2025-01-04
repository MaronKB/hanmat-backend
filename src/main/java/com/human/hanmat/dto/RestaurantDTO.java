package com.human.hanmat.dto;

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
    private Date regDate;
    private String isClosed;

    @Builder
    public RestaurantDTO(int id, String name, String lmmAddr, String roadAddr,Date regDate, String isClosed) {
        this.id = id;
        this.name = name;
        this.lmmAddr = lmmAddr;
        this.roadAddr = roadAddr;
        this.regDate = regDate;
        this.isClosed = isClosed;
    }
}
