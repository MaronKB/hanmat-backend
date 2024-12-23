package com.human.hanmat.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FoodDTO {
    private String id;
    private String name;
    private String dscrn;
    private String category;
    private String image;
}
