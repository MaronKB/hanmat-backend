package com.human.hanmat.service;

import com.human.hanmat.dto.LocationDTO;
import com.human.hanmat.dto.RestaurantDTO;
import com.human.hanmat.entity.Restaurant;
import com.human.hanmat.repository.RestaurantRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;

    public void add(Restaurant restaurant) {
        restaurantRepository.save(restaurant);
    }
    public void addMany(List<Restaurant> restaurants) {
        restaurantRepository.saveAll(restaurants);
    }

    public Restaurant read(int id) {
        return restaurantRepository.findById((long) id).orElse(null);
    }

    public void update(Restaurant restaurant) {
        restaurantRepository.save(restaurant);
    }

    public void delete() {
        System.out.println("delete");
    }

    public List<Restaurant> findAll() {
        return restaurantRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<RestaurantDTO> getPage(int page, int size, String sort) {
        System.out.println("page: " + page + ", size: " + size + ", sort: " + sort);
        List<Restaurant> restaurantPage = restaurantRepository.findAllByOrderByAsc((page - 1) * size, (page) * size, sort);
        List<RestaurantDTO> restaurantDTOList = new ArrayList<>();
        for (Restaurant restaurant: restaurantPage) {
            restaurantDTOList.add(new RestaurantDTO(restaurant));
        }
        return restaurantDTOList;
    }

    public List<RestaurantDTO> search(LocationDTO location) {
        double latitude = location.getLatitude();
        double longitude = location.getLongitude();
        double minLatitude = latitude - 0.002;
        double maxLatitude = latitude + 0.002;
        double minLongitude = longitude - 0.002;
        double maxLongitude = longitude + 0.002;
        List<RestaurantDTO> restaurantList = new ArrayList<>();

        List<Restaurant> restaurants = restaurantRepository.findByLocation(minLatitude, maxLatitude, minLongitude, maxLongitude);

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

    public int getTotal() {
        return (int) restaurantRepository.count();
    }


//    검색
    @Transactional(readOnly = true)
    public List<RestaurantDTO> searchByCategory(String category, String keyword, int page, int size) {
        int startRow = (page - 1) * size + 1;
        int endRow = page * size;

        List<Restaurant> restaurantPage;
        if ("regDate".equalsIgnoreCase(category)) {
            restaurantPage = restaurantRepository.findByRegDate(keyword, endRow, startRow);
        } else if ("closed".equalsIgnoreCase(category)) {
            restaurantPage = restaurantRepository.findByClosed("폐업".equalsIgnoreCase(keyword) ? "Y" : "N", endRow, startRow);
        } else {
            restaurantPage = restaurantRepository.findByField(category, keyword, endRow, startRow);
        }

        List<RestaurantDTO> restaurantDTOList = new ArrayList<>();
        for (Restaurant restaurant : restaurantPage) {
            restaurantDTOList.add(new RestaurantDTO(restaurant));
        }
        return restaurantDTOList;
    }

    @Transactional(readOnly = true)
    public int getTotalByCategory(String category, String keyword) {
        if ("regDate".equalsIgnoreCase(category)) {
            return restaurantRepository.countByRegDate(keyword);
        } else if ("closed".equalsIgnoreCase(category)) {
            return restaurantRepository.countByClosed("폐업".equalsIgnoreCase(keyword) ? "Y" : "N");
        } else {
            return restaurantRepository.countByField(category, keyword);
        }
    }
}
