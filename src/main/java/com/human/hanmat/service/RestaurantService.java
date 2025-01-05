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
        double minLatitude = latitude - 0.01;
        double maxLatitude = latitude + 0.01;
        double minLongitude = longitude - 0.01;
        double maxLongitude = longitude + 0.01;
        List<RestaurantDTO> restaurantList = new ArrayList<>();

        List<Restaurant> restaurants = restaurantRepository.findByLocation(minLatitude, maxLatitude, minLongitude, maxLongitude);

        System.out.println("size: " + restaurants.size());
        for (Restaurant restaurant: restaurants) {
            RestaurantDTO restaurantDTO = new RestaurantDTO();
            restaurantDTO.setId(restaurant.getId());
            restaurantDTO.setName(restaurant.getName());
            restaurantDTO.setLmmAddr(restaurant.getLmmAddr());
            restaurantDTO.setRoadAddr(restaurant.getRoadAddr());
            restaurantDTO.setLatitude(restaurant.getY());
            restaurantDTO.setLongitude(restaurant.getX());
            restaurantDTO.setClosed(Objects.equals(restaurant.getIsClosed(), "Y"));
            restaurantList.add(restaurantDTO);
        };
        return restaurantList;
    }
}
