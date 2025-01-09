package com.human.hanmat.controller;

import com.human.hanmat.dto.Page;
import com.human.hanmat.dto.PostDTO;
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
}
