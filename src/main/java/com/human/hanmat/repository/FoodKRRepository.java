package com.human.hanmat.repository;

import com.human.hanmat.entity.FoodKR;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodKRRepository extends JpaRepository<FoodKR, Long> {

}
