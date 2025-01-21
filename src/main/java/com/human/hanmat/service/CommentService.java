package com.human.hanmat.service;

import com.human.hanmat.dto.CommentDTO;
import com.human.hanmat.entity.Comment;
import com.human.hanmat.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;

    // 댓글 저장 메서드
    public CommentDTO save(CommentDTO commentDTO) {
        if (commentDTO.getRegBy() == null || commentDTO.getRegBy().isEmpty()) {
            throw new IllegalArgumentException("regBy 값이 NULL이거나 비어 있습니다!");
        }

        Comment comment = new Comment();
        // 필드 매핑
        comment.setAuthor(commentDTO.getRegBy()); // author에 설정
        comment.setContent(commentDTO.getContent()); // content 설정
        comment.setPostId(commentDTO.getPostId()); // postId 설정
        comment.setRegDate(new java.sql.Date(System.currentTimeMillis())); // 댓글 등록 날짜
        comment.setRegBy(commentDTO.getRegBy()); //  regBy
        comment.setIsHidden("N");
        comment.setIsReported("N");
        comment.setIsDeleted("N");

        Comment savedComment = commentRepository.save(comment); // 데이터 저장

        System.out.println("Comment saved successfully: " + savedComment); // 로그 확인
        return new CommentDTO(savedComment); // 저장된 댓글 반환
    }

    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    public List<CommentDTO> getPage(int page, int size, String sort) {
        List<Comment> commentPage = commentRepository.findAllByOrderByAsc((page - 1) * size, (page) * size, sort);
        List<CommentDTO> commentDTOList = new ArrayList<>();
        for (Comment comment: commentPage) {
            commentDTOList.add(new CommentDTO(comment));
        }
        return commentDTOList;
    }

    public int getTotal() {
        return (int) commentRepository.count();
    }
}

