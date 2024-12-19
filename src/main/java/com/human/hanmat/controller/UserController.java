package com.human.hanmat.controller;

import com.human.hanmat.entity.Response;
import com.human.hanmat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public Response<?> findAll() {
        return new Response<>(userService.findAll(), "Success", true, null);
    }

}
