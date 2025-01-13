package com.human.hanmat.repository;

import com.human.hanmat.entity.Restaurant;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;
import java.util.List;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    @Query(value = "SELECT * FROM (SELECT ROWNUM RN, T_RESTAURANT.* FROM HANMAT.T_RESTAURANT ORDER BY ?3) WHERE RN BETWEEN ?1 AND ?2", nativeQuery = true)
    List<Restaurant> findAllByOrderByAsc(int start, int end, String sort);

    @Query(value = "SELECT * FROM T_RESTAURANT WHERE RESTAURANT_Y > ?1 AND RESTAURANT_Y < ?2 AND RESTAURANT_X > ?3 AND RESTAURANT_X < ?4", nativeQuery = true)
    List<Restaurant> findByLocation(double minY, double maxY, double minX, double maxX);

//    검색
    @Query(value = "SELECT * FROM ( " +
            "SELECT ROWNUM AS RN, T.* FROM ( " +
            "SELECT * FROM T_RESTAURANT WHERE LOWER(?1) LIKE LOWER('%' || ?2 || '%') ORDER BY RESTAURANT_ID ASC " +
            ") T WHERE ROWNUM <= ?3 " +
            ") WHERE RN >= ?4",
            nativeQuery = true)
    List<Restaurant> findByField(String fieldName, String keyword, int endRow, int startRow);

    @Query(value = "SELECT COUNT(*) FROM T_RESTAURANT WHERE LOWER(?1) LIKE LOWER('%' || ?2 || '%')",
            nativeQuery = true)
    int countByField(String fieldName, String keyword);

    @Query(value = "SELECT * FROM ( " +
            "SELECT ROWNUM AS RN, T.* FROM ( " +
            "SELECT * FROM T_RESTAURANT WHERE RESTAURANT_REG_DATE LIKE '%' || ?1 || '%' ORDER BY RESTAURANT_ID ASC " +
            ") T WHERE ROWNUM <= ?2 " +
            ") WHERE RN >= ?3",
            nativeQuery = true)
    List<Restaurant> findByRegDate(String keyword, int endRow, int startRow);

    @Query(value = "SELECT COUNT(*) FROM T_RESTAURANT WHERE RESTAURANT_REG_DATE LIKE '%' || ?1 || '%'",
            nativeQuery = true)
    int countByRegDate(String keyword);

    @Query(value = "SELECT * FROM ( " +
            "SELECT ROWNUM AS RN, T.* FROM ( " +
            "SELECT * FROM T_RESTAURANT WHERE RESTAURANT_IS_CLOSED = ?1 ORDER BY RESTAURANT_ID ASC " +
            ") T WHERE ROWNUM <= ?2 " +
            ") WHERE RN >= ?3",
            nativeQuery = true)
    List<Restaurant> findByClosed(String closed, int endRow, int startRow);

    @Query(value = "SELECT COUNT(*) FROM T_RESTAURANT WHERE RESTAURANT_IS_CLOSED = ?1",
            nativeQuery = true)
    int countByClosed(String closed);



}
