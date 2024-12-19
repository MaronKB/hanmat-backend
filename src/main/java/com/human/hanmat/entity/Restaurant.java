package com.human.hanmat.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "restaurant", schema = "hanmat")
public class Restaurant {
    @Id
    @Column(name = "restaurant_id")
    private int id;

    @Column(name = "restaurant_name")
    private String name;

    @Column(name = "restaurant_lmm_addr")
    private String lmmAddr;

    @Column(name = "restaurant_road_addr")
    private String roadAddr;

    @Column(name = "restaurant_x")
    private double x;

    @Column(name = "restaurant_y")
    private double y;

    @Column(name = "restaurant_reg_date")
    private String regDate;

    @Column(name = "restaurant_reg_by")
    private String regBy;

    @Column(name = "restaurant_last_mod_date")
    private String lastModDate;

    @Column(name = "restaurant_last_mod_by")
    private String lastModBy;

    @Column(name = "restaurant_is_closed")
    private boolean isClosed;
}
