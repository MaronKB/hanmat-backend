package com.human.hanmat.entity;

import com.human.hanmat.dto.PostDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.sql.Date;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "t_post", schema = "hanmat")
public class Post {
    @Id
    @Column(name = "post_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "post_title")
    private String title;

    @Column(name = "post_author")
    private String author;

    @Column(name = "post_content")
    private String content;

    @Column(name = "post_rating")
    private int rating;

    @Column(name = "post_restaurant_id")
    private int restaurantId;

    @Column(name = "post_image_1")
    private String image1;

    @Column(name = "post_image_2")
    private String image2;

    @Column(name = "post_image_3")
    private String image3;

    @Column(name = "post_image_4")
    private String image4;

    @Column(name = "post_reg_date")
    private Date regDate;

    @Column(name = "post_reg_by")
    private String regBy;

    @Column(name = "post_mod_date")
    private Date modDate;

    @Column(name = "post_mod_by")
    private String modBy;

    @Column(name = "post_del_date")
    private Date delDate;

    @Column(name = "post_del_by")
    private String delBy;

    @Column(name = "post_is_hidden")
    private String isHidden;

    @Column(name = "post_is_reported")
    private String isReported;

    @Column(name = "post_is_deleted")
    private String isDeleted;


    public Post(PostDTO post) {
        System.out.println("Mapping rating value from DTO: " + post.getRating()); // 값 확인
        this.id = post.getId();
        this.title = post.getTitle();
        this.author = post.getAuthor();
        this.content = post.getContent();
        this.rating = post.getRating();
        this.restaurantId = post.getRestaurantId();
        this.image1 = post.getImage1();
        this.image2 = post.getImage2();
        this.image3 = post.getImage3();
        this.image4 = post.getImage4();

        if (post.getRegDate() == null) {
            this.regDate = new Date(System.currentTimeMillis());
        } else {
            this.regDate = post.getRegDate();
        }
        if (post.getRegBy() == null) {
            this.regBy = post.getAuthor().isEmpty() ?  "system@hanmat.com" : post.getAuthor();
        } else {
            this.regBy = post.getRegBy();
        }

        this.modDate = post.getModDate();
        this.modBy = post.getModBy();

        this.isHidden = post.isHidden() ? "Y" : "N";
        this.isReported = post.isReported() ? "Y" : "N";
        this.isDeleted = post.isDeleted() ? "Y" : "N";
    }
}
