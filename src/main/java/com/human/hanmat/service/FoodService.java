package com.human.hanmat.service;

import com.human.hanmat.dto.FoodDTO;
import com.human.hanmat.entity.Food;
import com.human.hanmat.entity.FoodEN;
import com.human.hanmat.entity.FoodJP;
import com.human.hanmat.entity.FoodKR;
import com.human.hanmat.repository.FoodKRRepository;
import com.human.hanmat.repository.FoodENRepository;
import com.human.hanmat.repository.FoodJPRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FoodService {
    @Autowired
    private FoodKRRepository foodKRRepository;

    @Autowired
    private FoodENRepository foodENRepository;

    @Autowired
    private FoodJPRepository foodJPRepository;

    public List<FoodDTO> getFoodList(String lang) {
        List<FoodDTO> foodList = new ArrayList<>();
        switch (lang) {
            case "ko" -> {
                List<FoodKR> foodKRList = foodKRRepository.findAll();
                for (FoodKR foodKR : foodKRList) {
                    System.out.println("foodKR: " + foodKR);
                    foodList.add(new FoodDTO(foodKR));
                }
            }
            case "en" -> {
                List<FoodEN> foodENList = foodENRepository.findAll();
                for (FoodEN foodEN : foodENList) {
                    System.out.println("foodEN: " + foodEN);
                    foodList.add(new FoodDTO(foodEN));
                }
            }
            case "jp" -> {
                List<FoodJP> foodJPList = foodJPRepository.findAll();
                for (FoodJP foodJP : foodJPList) {
                    System.out.println("foodJP: " + foodJP);
                    foodList.add(new FoodDTO(foodJP));
                }
            }
        }
        return foodList;
    }
}
