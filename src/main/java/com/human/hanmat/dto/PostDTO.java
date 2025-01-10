package com.human.hanmat.dto;

import com.human.hanmat.entity.Post;
import lombok.*;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostDTO {
    private int id;
    private String title;
    private String author;
    private String content;
    private int rating;
    private int restaurantId;
    private String image1;
    private String image2;
    private String image3;
    private String image4;
    private Date regDate;
    private String regBy;
    private Date modDate;
    private String modBy;
    private boolean isHidden;
    private boolean isReported;
    private boolean isDeleted;

    public PostDTO(Post post) {
        this.rating = post.getRating();
        this.id = post.getId();
        this.title = post.getTitle();
        this.author = post.getAuthor();
        this.content = post.getContent();
        this.restaurantId = post.getRestaurantId();
        this.image1 = post.getImage1();
        this.image2 = post.getImage2();
        this.image3 = post.getImage3();
        this.image4 = post.getImage4();
        this.regDate = post.getRegDate();
        this.regBy = post.getRegBy();
        this.modDate = post.getModDate();
        this.modBy = post.getModBy();
        this.isHidden = post.getIsHidden().equals("Y");
        this.isReported = post.getIsReported().equals("Y");
        this.isDeleted = post.getIsDeleted().equals("Y");
    }
}
