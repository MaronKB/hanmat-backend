package com.human.hanmat.controller;

import com.human.hanmat.dto.FoodDTO;
import com.human.hanmat.entity.Response;
import com.human.hanmat.service.FoodService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/food")
public class FoodController {
    @Autowired
    private FoodService foodService;

    @GetMapping("/all/{lang}")
    public Response<List<FoodDTO>> getAllFoodList(@PathVariable String lang) {
        List<FoodDTO> foodList = foodService.getAllFoodList(lang);
        if (foodList == null) {
            return new Response<>(null, "Fail", false, null);
        }
        return new Response<>(foodService.getAllFoodList(lang), "Success", true, null);
    }
}
