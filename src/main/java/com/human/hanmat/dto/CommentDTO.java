package com.human.hanmat.dto;

import com.human.hanmat.entity.Comment;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
public class CommentDTO {
    private Long id;
    private Long postId;
    private String content;
    private Date regDate;
    private String regBy;
    private Date delDate;
    private String delBy;
    private boolean isHidden;
    private boolean isReported;
    private boolean isDeleted;

    public CommentDTO(Comment comment) {
        this.id = comment.getId();
        this.postId = comment.getPostId();
        this.content = comment.getContent();
        this.regDate = comment.getRegDate();
        this.regBy = comment.getRegBy();
        this.delDate = comment.getDelDate();
        this.delBy = comment.getDelBy();
        this.isHidden = comment.getIsHidden().equals("Y");
        this.isReported = comment.getIsReported().equals("Y");
        this.isDeleted = comment.getIsDeleted().equals("Y");
    }
}



