package com.human.hanmat.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "T_COMMENT", schema = "HANMAT")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "comment_id_seq")
    @SequenceGenerator(name = "comment_id_seq", sequenceName = "HANMAT_SEQ_COMMENT_ID", allocationSize = 1)
    @Column(name = "comment_id")
    private Long id; // 댓글의 Primary Key

    @Column(name = "comment_author")
    private String author;

    @Column(name = "comment_content")
    private String content;

    @Column(name = "comment_post_id")
    private Long postId;

    @Column(name = "comment_reg_date")
    private Date regDate;

    @Column(name = "comment_reg_by")
    private String regBy;

    @Column(name = "comment_del_date")
    private Date delDate;

    @Column(name = "comment_del_by")
    private String delBy;

    @Column(name = "comment_is_hidden")
    private String isHidden;

    @Column(name = "comment_is_reported")
    private String isReported;

    @Column(name = "comment_is_deleted")
    private String isDeleted;
}


