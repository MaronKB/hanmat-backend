package com.human.hanmat.repository;

import com.human.hanmat.entity.FoodEN;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodENRepository extends JpaRepository<FoodEN, Long> {

}
