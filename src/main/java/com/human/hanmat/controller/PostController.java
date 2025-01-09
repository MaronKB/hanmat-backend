package com.human.hanmat.controller;

import com.human.hanmat.entity.Post;
import com.human.hanmat.entity.Response;
import com.human.hanmat.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {
    @Autowired
    private PostService postService;

    @GetMapping("/all")
    public Response<?> findAll() {
        return new Response<>(postService.findAll(), "Success", true, null);
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
    public Response<Post> saveReview(@RequestBody Post post) {
        Post savedPost = postService.saveReview(post);
        return new Response<>(savedPost, "Review saved successfully", true, null);
    }

}
