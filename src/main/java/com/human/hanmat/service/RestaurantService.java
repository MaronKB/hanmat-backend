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

    public List<RestaurantDTO> findByName(String name) {
        List<Restaurant> restaurants = restaurantRepository.findByName(name);
        List<RestaurantDTO> restaurantDTOList = new ArrayList<>();
        for (Restaurant restaurant: restaurants) {
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

        switch (category.toLowerCase()) {
            case "name":
                restaurantPage = restaurantRepository.findByName(keyword, endRow, startRow);
                break;
            case "location":
                restaurantPage = restaurantRepository.findByLocation(keyword, endRow, startRow);
                break;
            case "roadaddress":
                restaurantPage = restaurantRepository.findByRoadAddress(keyword, endRow, startRow);
                break;
            case "regdate":
                restaurantPage = restaurantRepository.findByRegDate(keyword, endRow, startRow);
                break;
            case "closed":
                String closedStatus = keyword.equals("폐업") ? "Y" : keyword.equals("영업 중") ? "N" : null;
                if (closedStatus == null) {
                    throw new IllegalArgumentException("Invalid value for closed: " + keyword);
                }
                restaurantPage = restaurantRepository.findByClosed(closedStatus, endRow, startRow);
                break;
            default:
                throw new IllegalArgumentException("Invalid category: " + category);
        }

        List<RestaurantDTO> restaurantDTOList = new ArrayList<>();
        for (Restaurant restaurant : restaurantPage) {
            restaurantDTOList.add(new RestaurantDTO(restaurant));
        }
        return restaurantDTOList;
    }

    @Transactional(readOnly = true)
    public int getTotalByCategory(String category, String keyword) {
        switch (category.toLowerCase()) {
            case "name":
                return restaurantRepository.countByName(keyword);
            case "location":
                return restaurantRepository.countByLocation(keyword);
            case "roadaddress":
                return restaurantRepository.countByRoadAddress(keyword);
            case "regdate":
                return restaurantRepository.countByRegDate(keyword);
            case "closed":
                String closedStatus = keyword.equals("폐업") ? "Y" : keyword.equals("영업 중") ? "N" : null;
                if (closedStatus == null) {
                    throw new IllegalArgumentException("Invalid value for closed: " + keyword);
                }
                return restaurantRepository.countByClosed(closedStatus);
            default:
                throw new IllegalArgumentException("Invalid category: " + category);
        }
    }

    @Transactional
    public void updateRestaurant(RestaurantDTO restaurantDTO) {
        Restaurant restaurant = restaurantRepository.findById((long) restaurantDTO.getId())
                .orElseThrow(() -> new IllegalArgumentException("Restaurant not found with ID: " + restaurantDTO.getId()));

        restaurant.setName(restaurantDTO.getName());
        restaurant.setLmmAddr(restaurantDTO.getLmmAddr());
        restaurant.setRoadAddr(restaurantDTO.getRoadAddr());
        restaurant.setRegDate(new java.sql.Date(restaurantDTO.getRegDate().getTime()));
        restaurant.setIsClosed(restaurantDTO.isClosed() ? "Y" : "N");

        restaurantRepository.save(restaurant);
    }

    @Transactional
    public void deleteRestaurants(List<Long> ids) {
        restaurantRepository.deleteAllById(ids);
    }

    @Transactional
    public void addRestaurant(RestaurantDTO restaurantDTO) {
        Restaurant restaurant = new Restaurant();
        restaurant.setName(restaurantDTO.getName());
        restaurant.setLmmAddr(restaurantDTO.getLmmAddr());
        restaurant.setRoadAddr(restaurantDTO.getRoadAddr());
        restaurant.setRegDate(restaurantDTO.getRegDate() != null ?
                new java.sql.Date(restaurantDTO.getRegDate().getTime()) :
                new java.sql.Date(System.currentTimeMillis()));
        restaurant.setIsClosed(restaurantDTO.isClosed() ? "Y" : "N");


        restaurant.setRegBy("system@hanmat.com");
        restaurant.setLastModBy("system@hanmat.com");
        restaurant.setLastModDate(new java.sql.Date(System.currentTimeMillis()));

        restaurantRepository.save(restaurant);
    }

}
