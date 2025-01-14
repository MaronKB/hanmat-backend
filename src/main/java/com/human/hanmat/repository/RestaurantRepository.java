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
        "SELECT * FROM T_RESTAURANT WHERE LOWER(RESTAURANT_NAME) LIKE LOWER('%' || ?1 || '%') ORDER BY RESTAURANT_ID ASC " +
        ") T WHERE ROWNUM <= ?2 " +
        ") WHERE RN >= ?3",
        nativeQuery = true)
List<Restaurant> findByName(String keyword, int endRow, int startRow);

    @Query(value = "SELECT COUNT(*) FROM T_RESTAURANT WHERE LOWER(RESTAURANT_NAME) LIKE LOWER('%' || ?1 || '%')",
            nativeQuery = true)
    int countByName(String keyword);

    // 위치(restaurant_location) 기반 검색
    @Query(value = "SELECT * FROM ( " +
            "SELECT ROWNUM AS RN, T.* FROM ( " +
            "SELECT * FROM T_RESTAURANT WHERE LOWER(RESTAURANT_LMM_ADDR) LIKE LOWER('%' || ?1 || '%') ORDER BY RESTAURANT_ID ASC " +
            ") T WHERE ROWNUM <= ?2 " +
            ") WHERE RN >= ?3",
            nativeQuery = true)
    List<Restaurant> findByLocation(String keyword, int endRow, int startRow);

    @Query(value = "SELECT COUNT(*) FROM T_RESTAURANT WHERE LOWER(RESTAURANT_LMM_ADDR) LIKE LOWER('%' || ?1 || '%')",
            nativeQuery = true)
    int countByLocation(String keyword);

    // 도로명 주소(restaurant_road_address) 기반 검색
    @Query(value = "SELECT * FROM ( " +
            "SELECT ROWNUM AS RN, T.* FROM ( " +
            "SELECT * FROM T_RESTAURANT WHERE LOWER(RESTAURANT_ROAD_ADDR) LIKE LOWER('%' || ?1 || '%') ORDER BY RESTAURANT_ID ASC " +
            ") T WHERE ROWNUM <= ?2 " +
            ") WHERE RN >= ?3",
            nativeQuery = true)
    List<Restaurant> findByRoadAddress(String keyword, int endRow, int startRow);

    @Query(value = "SELECT COUNT(*) FROM T_RESTAURANT WHERE LOWER(RESTAURANT_ROAD_ADDR) LIKE LOWER('%' || ?1 || '%')",
            nativeQuery = true)
    int countByRoadAddress(String keyword);

    @Query(value = "SELECT * FROM ( " +
            "SELECT ROWNUM AS RN, T.* FROM ( " +
            "SELECT * FROM T_RESTAURANT WHERE RESTAURANT_REG_DATE = TO_DATE(?1, 'YYYY-MM-DD') ORDER BY RESTAURANT_ID ASC " +
            ") T WHERE ROWNUM <= ?2 " +
            ") WHERE RN >= ?3",
            nativeQuery = true)
    List<Restaurant> findByRegDate(String date, int endRow, int startRow);

    @Query(value = "SELECT COUNT(*) FROM T_RESTAURANT WHERE RESTAURANT_REG_DATE = TO_DATE(?1, 'YYYY-MM-DD')",
            nativeQuery = true)
    int countByRegDate(String date);

    @Query(value = "SELECT * FROM ( " +
            "SELECT ROWNUM AS RN, T.* FROM ( " +
            "SELECT * FROM T_RESTAURANT WHERE LOWER(RESTAURANT_IS_CLOSED) LIKE LOWER('%' || ?1 || '%') ORDER BY RESTAURANT_ID ASC " +
            ") T WHERE ROWNUM <= ?2 " +
            ") WHERE RN >= ?3",
            nativeQuery = true)
    List<Restaurant> findByClosed(String keyword, int endRow, int startRow);

    @Query(value = "SELECT COUNT(*) FROM T_RESTAURANT WHERE LOWER(RESTAURANT_IS_CLOSED) LIKE LOWER('%' || ?1 || '%')",
            nativeQuery = true)
    int countByClosed(String keyword);

}
