package com.human.hanmat.controller;

import com.human.hanmat.dto.UserDTO;
import com.human.hanmat.entity.Response;
import com.human.hanmat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public Response<?> findAll() {
        return new Response<>(userService.findAll(), "Success", true, null);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public Response<?> login(@RequestBody UserDTO userData) {
        return new Response<>(userService.login(userData), "Success", true, null);
    }
}
