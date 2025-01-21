package com.human.hanmat.controller;

import com.human.hanmat.dto.Page;
import com.human.hanmat.dto.RestaurantDTO;
import com.human.hanmat.dto.UserDTO;
import com.human.hanmat.entity.Response;
import com.human.hanmat.entity.User;
import com.human.hanmat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/{email}")
    @ResponseStatus(HttpStatus.OK)
    public Response<?> findById(@PathVariable String email) {
        return new Response<>(userService.findByEmail(email), "Success", true, null);
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public Response<?> findAll(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "email") String sort) {
        List<UserDTO> userPage = userService.getPage(page, size, sort);
        int total = userService.getTotal();

        Page<UserDTO> pageData = new Page<>();
        pageData.setPage(page);
        pageData.setTotalPages((int) Math.ceil((double) total / size));
        pageData.setItems(userPage);
        pageData.setTotalItems(total);

        return new Response<>(pageData, "Success", true, null);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public Response<?> login(@RequestBody UserDTO userData) {
        return new Response<>(userService.login(userData), "Success", true, null);
    }

//    관리자 유저 수정
    @PutMapping("/update")
    public Response<?> updateUser(@RequestBody UserDTO userDTO) {
        try {
            userService.updateUser(userDTO);
            return new Response<>(null, "User updated successfully", true, null);
        } catch (IllegalArgumentException e) {
            return new Response<>(null, e.getMessage(), false, null);
        } catch (Exception e) {
            return new Response<>(null, "Failed to update user: " + e.getMessage(), false, null);
        }
    }



}
