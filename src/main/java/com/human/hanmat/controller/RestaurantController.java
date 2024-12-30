package com.human.hanmat.controller;

import com.human.hanmat.entity.Response;
import com.human.hanmat.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {
    @Autowired
    private RestaurantService restaurantService;

    @GetMapping("/all")
    public Response<?> findAll() {
        return new Response<>(restaurantService.findAll(), "Success", true, null);
    }
}
