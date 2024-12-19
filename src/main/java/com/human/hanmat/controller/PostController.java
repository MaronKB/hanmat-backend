package com.human.hanmat.controller;

import com.human.hanmat.entity.Response;
import com.human.hanmat.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/post")
public class PostController {
    @Autowired
    private PostService postService;

    @GetMapping("/all")
    public Response<?> findAll() {
        return new Response<>(postService.findAll(), "Success", true, null);
    }
}
