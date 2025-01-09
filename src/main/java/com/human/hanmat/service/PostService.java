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

    //전체 리뷰 조회
    public List<Post> findAll() {
        return postRepository.findAll();
    }

    // 사용자가 작성한 리뷰 조회
    public List<Post> findMyReviews(String userEmail) {
        return postRepository.findByRegBy(userEmail);
    }

    // 리뷰 저장
    public Post saveReview(Post post) {
        return postRepository.save(post);
    }
}

