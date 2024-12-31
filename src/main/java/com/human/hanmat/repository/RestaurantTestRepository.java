package com.human.hanmat.repository;

import com.human.hanmat.entity.Restaurant;
import com.human.hanmat.entity.RestaurantTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantTestRepository extends JpaRepository<RestaurantTest, Long> {
}
