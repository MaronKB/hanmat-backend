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
@Table(name = "post", schema = "hanmat")
public class Post {
    @Id
    @Column(name = "post_id")
    private int id;

    @Column(name = "post_title")
    private String title;

    @Column(name = "post_content")
    private String content;

    @Column(name = "post_image_1")
    private String image1;

    @Column(name = "post_image_2")
    private String image2;

    @Column(name = "post_image_3")
    private String image3;

    @Column(name = "post_image_4")
    private String image4;

    @Column(name = "post_reg_date")
    private String regDate;

    @Column(name = "post_reg_by")
    private String regBy;

    @Column(name = "post_mod_date")
    private String modDate;

    @Column(name = "post_mod_by")
    private String modBy;

    @Column(name = "post_del_date")
    private String delDate;

    @Column(name = "post_del_by")
    private String delBy;

    @Column(name = "post_is_hidden")
    private boolean isNotice;

    @Column(name = "post_is_reported")
    private boolean isReported;

    @Column(name = "post_is_deleted")
    private boolean isDeleted;
}
