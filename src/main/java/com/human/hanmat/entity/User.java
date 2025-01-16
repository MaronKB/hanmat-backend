package com.human.hanmat.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Table(name = "t_user", schema = "hanmat")
public class User {
    @Id
    @Column(name = "user_email")
    private String email;

    @Column(name = "user_nickname")
    private String nickname;

    @Column(name = "user_profile_image")
    private String profileImage;

    @Column(name = "user_reg_date")
    private Date regDate;

    @Column(name = "user_reg_by")
    private String regBy;

    @Column(name = "user_mod_date")
    private Date modDate;

    @Column(name = "user_mod_by")
    private String modBy;

    @Column(name = "user_del_date")
    private Date delDate;

    @Column(name = "user_del_by")
    private String delBy;

    @Column(name = "user_is_deleted")
    private String isDeleted;

    @Column(name = "user_is_admin")
    private String isAdmin;

    @Column(name = "user_is_buddy")
    private String isBuddy;

    @Column(name = "user_auto_translate")
    private String autoTranslate;

    @Column(name = "user_radius")
    private Integer radius;

    @Column(name = "user_interested")
    private String interested;

    @Column(name = "user_description")
    private String description;

    @Builder
    public User(String email, String nickname, String profileImage, Date regDate, String regBy, Date modDate, String modBy, Date delDate, String delBy, String isDeleted, String isAdmin, String isBuddy, String autoTranslate, Integer radius, String interested, String description) {
        this.email = email;
        this.nickname = nickname;
        this.profileImage = profileImage;
        this.regDate = regDate;
        this.regBy = regBy;
        this.modDate = modDate;
        this.modBy = modBy;
        this.delDate = delDate;
        this.delBy = delBy;
        this.isDeleted = isDeleted;
        this.isAdmin = isAdmin;
        this.isBuddy = isBuddy;
        this.autoTranslate = autoTranslate;
        this.radius = radius;
        this.interested = interested;
        this.description = description;
    }
}
