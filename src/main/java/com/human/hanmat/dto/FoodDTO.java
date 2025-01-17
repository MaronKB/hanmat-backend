package com.human.hanmat.dto;

import com.human.hanmat.entity.Food;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FoodDTO {
    private int id;
    private String name;
    private String dscrn;
    private String category;
    private String image;
    private String description;
    private Integer spicy;
    private Integer hanmat;

    public FoodDTO(int id, String name, String dscrn, String category, String image, String description, Integer spicy, Integer hanmat) {
        this.id = id;
        this.name = name;
        this.dscrn = dscrn;
        this.category = category;
        this.image = image;
        this.description = description;
        this.spicy = spicy;
        this.hanmat = hanmat;
    }

    public FoodDTO(Food food) {
        this.id = food.getId();
        this.name = food.getName();
        this.dscrn = food.getDscrn();
        this.category = food.getCategory();
        this.image = food.getImage();
        this.description = food.getDescription();
        this.spicy = food.getSpicy();
        this.hanmat = food.getHanmat();
    }
}
