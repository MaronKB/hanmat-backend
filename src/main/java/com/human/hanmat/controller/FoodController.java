package com.human.hanmat.controller;

import com.human.hanmat.dto.FoodDTO;
import com.human.hanmat.entity.FoodKR;
import com.human.hanmat.entity.Response;
import com.human.hanmat.service.FoodApiService;
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

    @Autowired
    private FoodApiService foodApiService;

    @GetMapping("/{lang}")
    public Response<List<FoodDTO>> getFoodList(HttpServletRequest request, @PathVariable String lang) {
        List<FoodDTO> foodList = foodService.getFoodList(lang);
        return new Response<>(foodList, "Success", true, null);
    }

    @Deprecated
    // @GetMapping("/all/{lang}")
    public Response<List<FoodDTO>> getAllFoodList(@PathVariable String lang) {
        String langStr = switch (lang) {
            case "ko" -> "korean";
            case "en" -> "eng";
            case "zh" -> "chchr";
            case "jp" -> "jpnse";
            default -> "eng";
        };
        System.out.println("lang: " + lang);
        System.out.println("langStr: " + langStr);
        List<FoodDTO> foodList = foodApiService.getAllFoodList(langStr, 60);

        System.out.println("foodList: " + foodList);
        if (foodList == null) {
            return new Response<>(null, "Fail", false, null);
        }
        return new Response<>(foodList, "Success", true, null);
    }
}
