package com.human.hanmat.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;

import java.sql.Date;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "T_BEST", schema = "HANMAT")
public class Best {
    @Id
    @Column(name = "best_id")
    private int id;

    @Column(name = "best_post_id")
    private int postId;

    @Column(name = "best_is_visible")
    private String isVisible;

    @Column(name = "best_reg_date")
    private Date regDate;

    public Best(Post post) {
        this.id = 0;
        this.postId = post.getId();
        this.isVisible = "Y";
        this.regDate = new Date(System.currentTimeMillis());
    }
}
