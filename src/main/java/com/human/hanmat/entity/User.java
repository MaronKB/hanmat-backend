package com.human.hanmat.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

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

    @Builder
    public User(String email, String nickname, Date regDate, String regBy, Date modDate, String modBy, Date delDate, String delBy, String isDeleted, String isAdmin) {
        this.email = email;
        this.nickname = nickname;
        this.regDate = regDate;
        this.regBy = regBy;
        this.modDate = modDate;
        this.modBy = modBy;
        this.delDate = delDate;
        this.delBy = delBy;
        this.isDeleted = isDeleted;
        this.isAdmin = isAdmin;
    }
}
