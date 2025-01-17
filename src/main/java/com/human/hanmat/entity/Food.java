package com.human.hanmat.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Food {
    private int id;
    private String name;
    private String dscrn;
    private String category;
    private String image;
    private String description;
    private Integer spicy;
    private Integer hanmat;
}
