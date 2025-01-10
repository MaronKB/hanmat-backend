package com.human.hanmat.controller;

import com.human.hanmat.dto.Page;
import com.human.hanmat.dto.PostDTO;
import com.human.hanmat.entity.Post;
import com.human.hanmat.entity.Response;
import com.human.hanmat.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {
    @Autowired
    private PostService postService;


    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public Response<?> findAll(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "email") String sort) {
        List<PostDTO> postPage = postService.getPage(page, size, sort);
        int total = postService.getTotal();

        Page<PostDTO> pageData = new Page<>();
        pageData.setPage(page);
        pageData.setTotalPages((int) Math.ceil((double) total / size));
        pageData.setItems(postPage);
        pageData.setTotalItems(total);

        return new Response<>(pageData, "Success", true, null);
    }

    //2. 특정 사용자가 작성한 리뷰 조회
    @GetMapping("/my")
    public Response<List<Post>> findMyReviews(@RequestParam(required = false) String userEmail) {
        if (userEmail == null || userEmail.isEmpty()) {
            return new Response<>(null, "User email is required", false, "400");
        }
        return new Response<>(postService.findMyReviews(userEmail), "Success", true, null);
    }

    // 3. 리뷰 저장
    @PostMapping("/save")
    public Response<PostDTO> saveReview(@RequestBody PostDTO post) {
        // 요청으로 전달된 데이터 확인 (로그 출력)
        System.out.println("Received PostDTO: " + post);
        System.out.println("Received Rating: " + post.getRating()); // rating 필드 확인

        PostDTO savedPost = postService.saveReview(post);
        return new Response<>(savedPost, "Review saved successfully", true, null);
    }

}
