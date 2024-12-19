package com.human.hanmat.service;

import com.human.hanmat.entity.Comment;
import com.human.hanmat.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;

    public void write() {
        System.out.println("write");
    }

    public void read() {
        System.out.println("read");
    }

    public void update() {
        System.out.println("update");
    }

    public void delete() {
        System.out.println("delete");
    }

    public List<Comment> findAll() {
        return commentRepository.findAll();
    }
}
