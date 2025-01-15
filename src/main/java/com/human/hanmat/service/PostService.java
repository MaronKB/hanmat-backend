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

    private String getSortParameter(String sort) {
        switch (sort) {
            case "id":
                return "post_id";
            case "title":
                return "post_title";
            case "author":
                return "post_author";
            case "rating":
                return "post_rating";
            case "restaurantId":
                return "post_restaurant_id";
            case "regDate":
                return "post_reg_date";
            case "regBy":
                return "post_reg_by";
            case "modDate":
                return "post_mod_date";
            case "modBy":
                return "post_mod_by";
            default:
                return "post_id";
        }
    }

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
        List<Post> postPage = (sort.equalsIgnoreCase("new"))
                ? postRepository.findAllOrderByIdDesc((page - 1) * size + 1, (page) * size)
                : postRepository.findAllOrderByIdAsc((page - 1) * size + 1, (page) * size);

        List<PostDTO> postDTOList = new java.util.ArrayList<>();
        for (Post post: postPage) {
            postDTOList.add(new PostDTO(post));
        }
        return postDTOList;
    }

    public List<PostDTO> getPage(int page, int size, String sort, String email) {
        List<Post> postPage = (sort.equalsIgnoreCase("new")
                ? postRepository.findAllByEmailOrderByIdDesc((page - 1) * size + 1, (page) * size, email)
                : postRepository.findAllByEmailOrderByIdAsc((page - 1) * size + 1, (page) * size, email));

        List<PostDTO> postDTOList = new java.util.ArrayList<>();
        for (Post post: postPage) {
            postDTOList.add(new PostDTO(post));
        }
        return postDTOList;
    }

    public List<PostDTO> findAllByRestaurantId(Long restaurantId, int page, int size, String sort) {
        List<Post> postPage = (sort.equalsIgnoreCase("new"))
                ? postRepository.findAllByRestaurantIdOrderByIdDesc(restaurantId, (page - 1) * size + 1, (page) * size)
                : postRepository.findAllByRestaurantIdOrderByIdAsc(restaurantId, (page - 1) * size + 1, (page) * size);

        List<PostDTO> postDTOList = new java.util.ArrayList<>();
        for (Post post: postPage) {
            postDTOList.add(new PostDTO(post));
        }
        return postDTOList;
    }

    public int getTotal() {
        return (int) postRepository.count();
    }

    public int getTotalByEmail(String email) {
        return postRepository.countAllByEmail(email);
    }

    public int getTotalByRestaurantId(Long restaurantId) {
        return postRepository.countAllByRestaurantId(restaurantId);
    }
}

