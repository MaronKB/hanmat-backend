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
@Table(name = "t_post", schema = "hanmat")
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
}
