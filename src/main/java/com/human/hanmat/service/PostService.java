package com.human.hanmat.service;

import com.human.hanmat.entity.Post;
import com.human.hanmat.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

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

    public List<Post> findAll() {
        return postRepository.findAll();
    }
}
