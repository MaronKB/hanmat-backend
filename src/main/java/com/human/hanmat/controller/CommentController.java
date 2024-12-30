package com.human.hanmat.controller;

import com.human.hanmat.entity.Response;
import com.human.hanmat.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @GetMapping("/all")
    public Response<?> findAll() {
        return new Response<>(commentService.findAll(), "Success", true, null);
    }
}
