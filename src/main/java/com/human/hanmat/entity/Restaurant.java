package com.human.hanmat.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "t_restaurant", schema = "hanmat")
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
    private Date regDate;

    @Column(name = "restaurant_reg_by")
    private String regBy;

    @Column(name = "restaurant_last_mod_date")
    private Date lastModDate;

    @Column(name = "restaurant_last_mod_by")
    private String lastModBy;

    @Column(name = "restaurant_is_closed")
    private String isClosed;

    @Column(name = "restaurant_description")
    private String description;
}
