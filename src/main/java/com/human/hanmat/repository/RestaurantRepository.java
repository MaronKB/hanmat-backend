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
    @Query(value = "SELECT * FROM T_RESTAURANT WHERE LOWER(:fieldName) LIKE LOWER(CONCAT('%', :keyword, '%')) ORDER BY RESTAURANT_ID ASC OFFSET :offset ROWS FETCH NEXT :limit ROWS ONLY", nativeQuery = true)
    List<Restaurant> findByField(@Param("fieldName") String fieldName,
                                 @Param("keyword") String keyword,
                                 @Param("offset") int offset,
                                 @Param("limit") int limit);

    @Query(value = "SELECT COUNT(*) FROM T_RESTAURANT WHERE LOWER(:fieldName) LIKE LOWER(CONCAT('%', :keyword, '%'))", nativeQuery = true)
    int countByField(@Param("fieldName") String fieldName, @Param("keyword") String keyword);

    @Query(value = "SELECT * FROM T_RESTAURANT WHERE RESTAURANT_REG_DATE LIKE CONCAT('%', :keyword, '%') ORDER BY RESTAURANT_ID ASC OFFSET :offset ROWS FETCH NEXT :limit ROWS ONLY", nativeQuery = true)
    List<Restaurant> findByRegDate(@Param("keyword") String keyword,
                                   @Param("offset") int offset,
                                   @Param("limit") int limit);

    @Query(value = "SELECT COUNT(*) FROM T_RESTAURANT WHERE RESTAURANT_REG_DATE LIKE CONCAT('%', :keyword, '%')", nativeQuery = true)
    int countByRegDate(@Param("keyword") String keyword);

    @Query(value = "SELECT * FROM T_RESTAURANT WHERE RESTAURANT_IS_CLOSED = :closed ORDER BY RESTAURANT_ID ASC OFFSET :offset ROWS FETCH NEXT :limit ROWS ONLY", nativeQuery = true)
    List<Restaurant> findByClosed(@Param("closed") String closed,
                                  @Param("offset") int offset,
                                  @Param("limit") int limit);

    @Query(value = "SELECT COUNT(*) FROM T_RESTAURANT WHERE RESTAURANT_IS_CLOSED = :closed", nativeQuery = true)
    int countByClosed(@Param("closed") String closed);
}
