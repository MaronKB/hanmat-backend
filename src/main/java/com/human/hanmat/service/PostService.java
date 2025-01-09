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

    public List<PostDTO> getPage(int page, int size, String sort) {
        List<Post> postPage = postRepository.findAllByOrderByAsc((page - 1) * size, (page) * size, sort);
        List<PostDTO> postDTOList = new java.util.ArrayList<>();
        for (Post post: postPage) {
            postDTOList.add(new PostDTO(post));
        }
        return postDTOList;
    }

    public int getTotal() {
        return (int) postRepository.count();
    }
}
