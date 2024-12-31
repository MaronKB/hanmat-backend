package com.human.hanmat.service;

import com.human.hanmat.dto.LocationDTO;
import com.human.hanmat.dto.RestaurantDTO;
import com.human.hanmat.entity.Restaurant;
import com.human.hanmat.entity.RestaurantTest;
import com.human.hanmat.repository.RestaurantRepository;
import com.human.hanmat.repository.RestaurantTestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;
    private final RestaurantTestRepository restaurantTestRepository;

    public void add(Restaurant restaurant) {
        restaurantRepository.save(restaurant);
    }

    public Restaurant read(int id) {
        return restaurantRepository.findById((long) id).orElse(null);
    }

    public void update(Restaurant restaurant) {
        restaurantRepository.save(restaurant);
    }

    public void update(RestaurantTest restaurant) {
        restaurantTestRepository.save(restaurant);
    }

    public void delete() {
        System.out.println("delete");
    }

    public List<Restaurant> findAll() {
        return restaurantRepository.findAll();
    }

    public List<RestaurantDTO> search(LocationDTO location) {
        double latitude = location.getLatitude();
        double longitude = location.getLongitude();
        List<RestaurantDTO> restaurantList = new ArrayList<>();

        List<Restaurant> restaurants = restaurantRepository.findAll();
        for (Restaurant restaurant: restaurants) {
            double restaurantLatitude = restaurant.getX();
            System.out.println("restaurantLatitude: " + restaurantLatitude);
            double restaurantLongitude = restaurant.getY();
            System.out.println("restaurantLongitude: " + restaurantLongitude);
            double distance = Math.sqrt(Math.pow(restaurantLatitude - latitude, 2) + Math.pow(restaurantLongitude - longitude, 2));
            System.out.println("distance: " + distance);
            if (distance < 0.01) {
                RestaurantDTO restaurantDTO = new RestaurantDTO();
                restaurantDTO.setId(restaurant.getId());
                restaurantDTO.setName(restaurant.getName());
                restaurantDTO.setLmmAddr(restaurant.getLmmAddr());
                restaurantDTO.setRoadAddr(restaurant.getRoadAddr());
                restaurantDTO.setClosed(Objects.equals(restaurant.getIsClosed(), "Y"));
                restaurantList.add(restaurantDTO);
            }
        };
        return restaurantList;
    }
}
