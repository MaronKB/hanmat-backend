package com.human.hanmat.service;

import com.human.hanmat.entity.Restaurant;
import com.human.hanmat.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;

    public void add() {
        System.out.println("write");
    }

    public void read() {
        System.out.println("read");
    }

    public void update() {
        System.out.println("update");
    }

    public void delete() {
        System.out.println("delete");
    }

    public List<Restaurant> findAll() {
        return restaurantRepository.findAll();
    }
}
