package com.human.hanmat.repository;

import com.human.hanmat.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    @Query(value = "SELECT * FROM T_RESTAURANT WHERE RESTAURANT_Y > ?1 AND RESTAURANT_Y < ?2 AND RESTAURANT_X > ?3 AND RESTAURANT_X < ?4", nativeQuery = true)
    List<Restaurant> findByLocation(double minY, double maxY, double minX, double maxX);

    @Query(value = "SELECT * FROM T_RESTAURANT WHERE (RESTAURANT_LMM_ADDR LIKE %?1%) OR (RESTAURANT_ROAD_ADDR LIKE %?1%)", nativeQuery = true)
    List<Restaurant> findByCity(String city);
}
