package com.human.hanmat.service;

import com.human.hanmat.dto.PostDTO;
import com.human.hanmat.entity.Best;
import com.human.hanmat.entity.Post;
import com.human.hanmat.repository.BestRepository;
import com.human.hanmat.repository.PostRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final BestRepository bestRepository;

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

    public List<PostDTO> getBestPosts(int page, int size) {
        List<Post> bestPosts = postRepository.findAllBestOrderByDesc((page - 1) * size + 1, page * size);
        List<PostDTO> bestPostDTOs = new ArrayList<>();
        for (Post post: bestPosts) {
            bestPostDTOs.add(new PostDTO(post));
        }
        System.out.println(bestPostDTOs);
        return bestPostDTOs;
    }

    public List<PostDTO> getBestPostsByVisible(int page, int size) {
        List<Post> bestPosts = postRepository.findAllVisibleBestOrderByDesc((page - 1) * size + 1, page * size);
        List<PostDTO> bestPostDTOs = new ArrayList<>();
        for (Post post: bestPosts) {
            bestPostDTOs.add(new PostDTO(post));
        }
        return bestPostDTOs;
    }

    public PostDTO newBestPost(Long postId) {
        Post post = postRepository.findById(postId).orElse(null);
        if (post == null) {
            return null;
        }
        Best best = new Best(post);
        bestRepository.save(best);

        return new PostDTO(post);
    }

    public Best setBestPost(Long postId) {
        Best best = bestRepository.findById(postId).orElse(null);
        if (best == null) {
            return null;
        }
        best.setIsVisible(best.getIsVisible().equalsIgnoreCase("Y") ? "N" : "Y");
        bestRepository.save(best);
        return best;
    }

    public void deleteBestPost(Long postId) {
        bestRepository.deleteById(postId);
    }

    public int getTotal() {
        return (int) postRepository.count();
    }

    public int getTotalByEmail(String email) {
        return postRepository.countAllByEmail(email);
    }

    public int getTotalBestPosts() {
        return (int) bestRepository.count();
    }

    public int getTotalBestPostsByVisible() {
        return bestRepository.countAllVisible();
    }

    public int getTotalByRestaurantId(Long restaurantId) {
        return postRepository.countAllByRestaurantId(restaurantId);
    }

//    관리자 리뷰 수정
    @Transactional
    public void updatePost(PostDTO postDTO) {
        Post post = postRepository.findById((long) postDTO.getId())
                .orElseThrow(() -> new IllegalArgumentException("Post not found with ID: " + postDTO.getId()));


        post.setTitle(postDTO.getTitle());
        post.setContent(postDTO.getContent());
        post.setRegDate(new java.sql.Date(postDTO.getRegDate().getTime()));
        post.setIsHidden(postDTO.isHidden() ? "Y" : "N");
        post.setIsReported(postDTO.isReported() ? "Y" : "N");

        postRepository.save(post);
    }

}
