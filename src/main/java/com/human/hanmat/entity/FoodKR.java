package com.human.hanmat.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "t_food_kr", schema = "hanmat")
public class FoodKR extends Food {
    @Id
    @Column(name = "food_id")
    private int id;

    @Column(name = "food_name")
    private String name;

    @Column(name = "food_dscrn")
    private String dscrn;

    @Column(name = "food_category")
    private String category;

    @Column(name = "food_image")
    private String image;
}
