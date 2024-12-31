package com.human.hanmat.repository;

import com.human.hanmat.entity.FoodJP;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodJPRepository extends JpaRepository<FoodJP, Long> {

}
