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

//    // 사용자가 작성한 리뷰 조회
//    public List<Post> findMyReviews(String userEmail) {
//        return postRepository.findByRegBy(userEmail);
//    }

    // 특정 사용자가 작성한 리뷰 조회하기(정렬 조건 포함됨)
    public List<Post> findMyReviews(String userEmail, String sort) {
        List<Post> posts;
        switch (sort.toLowerCase()) { // 정렬 조건 처리
            case "new": // 추가: 최신순
                posts = postRepository.findByRegByOrderByPostIdDesc(userEmail);
                break;
            case "old": // 추가: 오래된순
                posts = postRepository.findByRegByOrderByRegDateAsc(userEmail);
                break;
            case "rating": // 수정: 별점 순
                posts = postRepository.findByRegByOrderByRatingDesc(userEmail);
                break;
            default: // 기본 정렬 없음
                posts = postRepository.findByRegBy(userEmail);
        }
        return posts;
    }

    // 리뷰 저장
    public PostDTO saveReview(PostDTO post) {
        Post newPost = new Post(post);
        System.out.println(newPost);
        Post savedPost = postRepository.save(newPost);
        return new PostDTO(savedPost);
    }

    // 페이지 요청 (전체,특정 사용자)
    public List<PostDTO> getPage(int page, int size, String sort, String email) {
        List<Post> postPage;
        int start = (page - 1) * size + 1;
        int end = page * size;

        switch (sort.toLowerCase()) {
            case "new":
                postPage = (email == null)
                        ? postRepository.findAllOrderByIdDesc(start, end)
                        : postRepository.findAllByEmailOrderByIdDesc(start, end, email);
                break;

            case "rating":
                postPage = (email == null)
                        ? postRepository.findAllOrderByRatingDesc(start, end)
                        : postRepository.findByRegByOrderByRatingDesc(email);
                break;

            case "old":
            default:
                postPage = (email == null)
                        ? postRepository.findAllOrderByIdAsc(start, end)
                        : postRepository.findAllByEmailOrderByIdAsc(start, end, email);
                break;
        }

        List<PostDTO> postDTOList = new java.util.ArrayList<>();
        postPage.forEach(post -> postDTOList.add(new PostDTO(post)));

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
