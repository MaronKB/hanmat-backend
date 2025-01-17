package com.human.hanmat.controller;

import com.human.hanmat.dto.Page;
import com.human.hanmat.dto.PostDTO;
import com.human.hanmat.entity.Best;
import com.human.hanmat.entity.Response;
import com.human.hanmat.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {
    @Autowired
    private PostService postService;

    private Page<PostDTO> parseListToPage(List<PostDTO> postPage, int total, int page, int size) {
        Page<PostDTO> pageData = new Page<>();
        pageData.setPage(page);
        pageData.setTotalPages((int) Math.ceil((double) total / size));
        pageData.setItems(postPage);
        pageData.setTotalItems(total);
        return pageData;
    };

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public Response<?> findAll(@RequestParam(defaultValue = "1") int page,
                               @RequestParam(defaultValue = "10") int size,
                               @RequestParam(defaultValue = "new") String sort
    ) {
        // sort 값 검증: "new", "old", "rating"만 허용
        if (!List.of("new", "old", "rating").contains(sort.toLowerCase())) {
            return new Response<>(null, "Invalid sort parameter", false, "400");
        }

        List<PostDTO> postPage = postService.getPage(page, size, sort, null);
        int total = postService.getTotal();

        Page<PostDTO> pageData = parseListToPage(postPage, total, page, size);

        return new Response<>(pageData, "Success", true, null);
    }

    //2. 특정 사용자가 작성한 리뷰 조회
    @GetMapping("/my")
    public Response<?> findMyReviews(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "new") String sort, @RequestParam(required = false) String email) {
        if (email == null || email.isEmpty()) {
            return new Response<>(null, "User email is required", false, "400");
        }

        List<PostDTO> postPage = postService.getPage(page, size, sort, email);
        int total = postService.getTotalByEmail(email);

        Page<PostDTO> pageData = parseListToPage(postPage, total, page, size);

        return new Response<>(pageData, "Success", true, null);
    }

    @GetMapping("/restaurant/{restaurantId}")
    public Response<?> findByRestaurantId(@PathVariable Long restaurantId, @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "new") String sort) {
        List<PostDTO> postPage = postService.findAllByRestaurantId(restaurantId, page, size, sort);
        int total = postService.getTotalByRestaurantId(restaurantId);

        Page<PostDTO> pageData = parseListToPage(postPage, total, page, size);

        return new Response<>(pageData, "Success", true, null);
    }

    // 3. 리뷰 저장
    @PostMapping("/save")
    public Response<PostDTO> saveReview(@RequestBody PostDTO post) {
        PostDTO savedPost = postService.saveReview(post);
        return new Response<>(savedPost, "Review saved successfully", true, null);
    }

    @GetMapping("/best")
    public Response<?> getAllBestPosts(@RequestParam(defaultValue = "true") boolean all, @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int size) {
        List<PostDTO> postPage = all
                ? postService.getBestPosts(page, size)
                : postService.getBestPostsByVisible(page, size);
        int total = all
                ? postService.getTotalBestPosts()
                : postService.getTotalBestPostsByVisible();

        Page<PostDTO> pageData = parseListToPage(postPage, total, page, size);

        return new Response<>(pageData, "Success", true, null);
    }

    @GetMapping("/best/{postId}")
    public Response<?> newBestPost(@PathVariable Long postId) {
        PostDTO post = postService.newBestPost(postId);
        return new Response<>(post, "Success", true, null);
    }

    @PatchMapping("/best/{postId}")
    public Response<?> setBestPost(@PathVariable Long postId) {
        Best best = postService.setBestPost(postId);
        return new Response<>(best, "Success", true, null);
    }

    @DeleteMapping("/best/{postId}")
    public Response<?> deleteBestPost(@PathVariable Long postId) {
        postService.deleteBestPost(postId);
        return new Response<>(null, "Success", true, null);
    }
}
