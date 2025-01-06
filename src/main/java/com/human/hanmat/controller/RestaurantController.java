package com.human.hanmat.controller;

import com.human.hanmat.dto.LocationDTO;
import com.human.hanmat.entity.Response;
import com.human.hanmat.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {
    @Autowired
    private RestaurantService restaurantService;

    @GetMapping("/all")
    public Response<?> findAll() {
        return new Response<>(restaurantService.findAll(), "Success", true, null);
    }

    @PostMapping("/nearby")
    public Response<?> getNearbyRestaurants(@RequestBody LocationDTO location) {
        return new Response<>(restaurantService.search(location), "Success", true, null);
    }
}
