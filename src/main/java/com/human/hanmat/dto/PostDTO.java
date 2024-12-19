package com.human.hanmat.dto;

import lombok.*;

@Data
@NoArgsConstructor
public class PostDTO {
    private int id;
    private String title;
    private String content;
    private String image1;
    private String image2;
    private String image3;
    private String image4;

    @Builder
    public PostDTO(int id, String title, String content, String image1, String image2, String image3, String image4) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.image1 = image1;
        this.image2 = image2;
        this.image3 = image3;
        this.image4 = image4;
    }
}
