package com.human.hanmat.controller;

import com.human.hanmat.dto.LocationDTO;
import com.human.hanmat.dto.RestaurantDTO;
import com.human.hanmat.entity.Response;
import com.human.hanmat.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import com.human.hanmat.dto.Page;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {
    @Autowired
    private RestaurantService restaurantService;

    @GetMapping("/all")
    @ResponseBody
    public Response<?> findAll(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "20") int size, @RequestParam(defaultValue = "name") String sort) {
        List<RestaurantDTO> restaurantPage = restaurantService.getPage(page, size, sort);
        int total = restaurantService.getTotal();

        Page<RestaurantDTO> pageData = new Page<>();
        pageData.setPage(page);
        pageData.setTotalPages((int) Math.ceil((double) total / size));
        pageData.setItems(restaurantPage);
        pageData.setTotalItems(total);

        return new Response<>(pageData, "Success", true, null);
    }

    @PostMapping("/nearby")
    public Response<?> getNearbyRestaurants(@RequestBody LocationDTO location) {
        return new Response<>(restaurantService.search(location), "Success", true, null);
    }

//    검색
    @GetMapping("/search")
    public Response<?> searchRestaurants(@RequestParam String category,
                                         @RequestParam String keyword,
                                         @RequestParam(defaultValue = "1") int page,
                                         @RequestParam(defaultValue = "20") int size) {
        List<RestaurantDTO> restaurantPage = restaurantService.searchByCategory(category, keyword, page, size);
        int total = restaurantService.getTotalByCategory(category, keyword);

        Page<RestaurantDTO> pageData = new Page<>();
        pageData.setPage(page);
        pageData.setTotalPages((int) Math.ceil((double) total / size));
        pageData.setItems(restaurantPage);
        pageData.setTotalItems(total);

        return new Response<>(pageData, "Success", true, null);
    }
}
