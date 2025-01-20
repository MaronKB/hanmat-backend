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

