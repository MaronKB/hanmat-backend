package com.human.hanmat.service;

import com.human.hanmat.dto.PostDTO;
import com.human.hanmat.entity.Post;
import com.human.hanmat.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public List<Post> findAll() {
        return postRepository.findAll();
    }

    // 사용자가 작성한 리뷰 조회
    public List<Post> findMyReviews(String userEmail) {
        return postRepository.findByRegBy(userEmail);
    }

    // 리뷰 저장
    public PostDTO saveReview(PostDTO post) {
        Post newPost = new Post(post);
        System.out.println(newPost);
        Post savedPost = postRepository.save(newPost);
        return new PostDTO(savedPost);
    }

    public List<PostDTO> getPage(int page, int size, String sort) {
        List<Post> postPage = postRepository.findAllByOrderByDesc((page - 1) * size + 1, (page) * size, sort);
        List<PostDTO> postDTOList = new java.util.ArrayList<>();
        for (Post post: postPage) {
            postDTOList.add(new PostDTO(post));
        }
        return postDTOList;
    }

    public List<PostDTO> getPage(int page, int size, String sort, String email) {
        List<Post> postPage = postRepository.findAllByEmailOrderByDesc((page - 1) * size + 1, (page) * size, sort, email);
        List<PostDTO> postDTOList = new java.util.ArrayList<>();
        for (Post post: postPage) {
            postDTOList.add(new PostDTO(post));
        }
        return postDTOList;
    }

    public int getTotal() {
        return (int) postRepository.count();
    }

    public int getTotal(String email) {
        return postRepository.findByRegBy(email).size();
    }
}

