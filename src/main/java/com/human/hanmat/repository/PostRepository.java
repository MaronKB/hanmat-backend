package com.human.hanmat.repository;

import com.human.hanmat.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    @Query(value = "SELECT * FROM (SELECT ROWNUM RN, T_POST.* FROM HANMAT.T_POST ORDER BY POST_ID DESC) WHERE RN >= ?1 AND RN <= ?2", nativeQuery = true)
    List<Post> findAllOrderByIdDesc(int start, int end);

    @Query(value = "SELECT * FROM (SELECT ROWNUM RN, T_POST.* FROM HANMAT.T_POST ORDER BY POST_ID ASC) WHERE RN >= ?1 AND RN <= ?2", nativeQuery = true)
    List<Post> findAllOrderByIdAsc(int start, int end);

    @Query(value = "SELECT * FROM (SELECT ROWNUM RN, T_POST.* FROM HANMAT.T_POST WHERE (POST_REG_BY = ?3 OR POST_AUTHOR = ?3) ORDER BY POST_ID DESC) WHERE RN >= ?1 AND RN <= ?2", nativeQuery = true)
    List<Post> findAllByEmailOrderByIdDesc(int start, int end, String email);

    @Query(value = "SELECT * FROM (SELECT ROWNUM RN, T_POST.* FROM HANMAT.T_POST WHERE (POST_REG_BY = ?3 OR POST_AUTHOR = ?3) ORDER BY POST_ID ASC) WHERE RN >= ?1 AND RN <= ?2", nativeQuery = true)
    List<Post> findAllByEmailOrderByIdAsc(int start, int end, String email);

    @Query(value = "SELECT COUNT(*) FROM HANMAT.T_POST WHERE POST_REG_BY = ?1 OR POST_AUTHOR = ?1", nativeQuery = true)
    int countAllByEmail(String email);

    @Query(value = "SELECT COUNT(*) FROM HANMAT.T_POST WHERE POST_RESTAURANT_ID = ?1", nativeQuery = true)
    int countAllByRestaurantId(Long restaurantId);

    @Query(value = "SELECT * FROM (SELECT ROWNUM RN, T_POST.* FROM HANMAT.T_POST WHERE POST_RESTAURANT_ID = ?1 ORDER BY POST_ID DESC) WHERE RN >= ?2 AND RN <= ?3", nativeQuery = true)
    List<Post> findAllByRestaurantIdOrderByIdDesc(Long restaurantId, int start, int end);

    @Query(value = "SELECT * FROM (SELECT ROWNUM RN, T_POST.* FROM HANMAT.T_POST WHERE POST_RESTAURANT_ID = ?1 ORDER BY POST_ID ASC) WHERE RN >= ?2 AND RN <= ?3", nativeQuery = true)
    List<Post> findAllByRestaurantIdOrderByIdAsc(Long restaurantId, int start, int end);

    // 1. 특정 사용자가 작성한 리뷰 조회
    @Query("SELECT p FROM Post p WHERE p.regBy = :regBy")
    List<Post> findByRegBy(@Param("regBy") String regBy);

    //2. 특정 사용자의 리뷰 (최신순)
    @Query("SELECT p FROM Post p WHERE p.regBy = :regBy ORDER BY p.id DESC")
    List<Post> findByRegByOrderByPostIdDesc(@Param("regBy") String regBy);

    //3. 특정 사용자의 리뷰(오래된순)
    @Query("SELECT p FROM Post p WHERE p.regBy = :regBy ORDER BY p.id Asc")
    List<Post> findByRegByOrderByRegDateAsc(@Param("regBy") String regBy);

    //4. 특정 사용자의 리뷰 (별점높은순)
    @Query("SELECT p FROM Post p WHERE p.regBy = :regBy ORDER BY p.rating DESC")
    List<Post> findByRegByOrderByRatingDesc(@Param("regBy") String regBy);

    @Query(value = "SELECT * FROM (SELECT ROWNUM RN, T_POST.* FROM HANMAT.T_POST ORDER BY POST_RATING DESC) WHERE RN >= ?1 AND RN <= ?2", nativeQuery = true)
    List<Post> findAllOrderByRatingDesc(int start, int end);

    @Query(value = "SELECT * FROM (SELECT ROWNUM RN, P.*, B.BEST_IS_VISIBLE, B.BEST_REG_DATE FROM HANMAT.T_POST P INNER JOIN T_BEST B ON P.POST_ID = B.BEST_POST_ID ORDER BY POST_ID DESC) WHERE RN >= ?1 AND RN <= ?2", nativeQuery = true)
    List<Post> findAllBestOrderByDesc(int start, int end);

    @Query(value = "SELECT * FROM (SELECT ROWNUM RN, P.*, B.BEST_IS_VISIBLE, B.BEST_REG_DATE FROM HANMAT.T_POST P INNER JOIN T_BEST B ON P.POST_ID = B.BEST_POST_ID ORDER BY POST_ID ASC) WHERE RN >= ?1 AND RN <= ?2", nativeQuery = true)
    List<Post> findAllBestOrderByAsc(int start, int end);

    @Query(value = "SELECT * FROM (SELECT ROWNUM RN, P.*, B.BEST_IS_VISIBLE, B.BEST_REG_DATE FROM HANMAT.T_POST P INNER JOIN T_BEST B ON P.POST_ID = B.BEST_POST_ID WHERE B.BEST_IS_VISIBLE = 'Y' ORDER BY POST_ID DESC) WHERE RN >= ?1 AND RN <= ?2", nativeQuery = true)
    List<Post> findAllVisibleBestOrderByDesc(int start, int end);

//    관리자 리뷰 검색
    @Query(value = """
        SELECT * FROM (
            SELECT ROWNUM RN, T_POST.* 
            FROM HANMAT.T_POST 
            WHERE 
                (POST_RESTAURANT_ID LIKE '%' || :keyword || '%' AND :category = 'restaurantId') OR
                (LOWER(POST_AUTHOR) LIKE LOWER('%' || :keyword || '%') AND :category = 'author') OR
                (POST_RATING = TO_NUMBER(:keyword) AND :category = 'rating') OR
                (LOWER(POST_TITLE) LIKE LOWER('%' || :keyword || '%') AND :category = 'title') OR
                (LOWER(POST_CONTENT) LIKE LOWER('%' || :keyword || '%') AND :category = 'content') OR
                (TO_CHAR(POST_REG_DATE, 'YYYY-MM-DD') LIKE '%' || :keyword || '%' AND :category = 'regDate') OR
                (POST_IS_HIDDEN = :keyword AND :category = 'isHidden') OR
                (POST_IS_REPORTED = :keyword AND :category = 'isReported')
            ORDER BY 
                CASE 
                    WHEN :sort = 'new' THEN POST_ID 
                    WHEN :sort = 'old' THEN POST_ID 
                    WHEN :sort = 'rating' THEN POST_RATING 
                END DESC
        ) 
        WHERE RN BETWEEN :start AND :end
        """, nativeQuery = true)
    List<Post> searchPosts(
            @Param("category") String category,
            @Param("keyword") String keyword,
            @Param("sort") String sort,
            @Param("start") int start,
            @Param("end") int end
    );

    @Query(value = """
    SELECT COUNT(*) FROM HANMAT.T_POST 
    WHERE 
        (POST_RESTAURANT_ID LIKE '%' || :keyword || '%' AND :category = 'restaurantId') OR
        (LOWER(POST_AUTHOR) LIKE LOWER('%' || :keyword || '%') AND :category = 'author') OR
        (POST_RATING = TO_NUMBER(:keyword) AND :category = 'rating') OR
        (LOWER(POST_TITLE) LIKE LOWER('%' || :keyword || '%') AND :category = 'title') OR
        (LOWER(POST_CONTENT) LIKE LOWER('%' || :keyword || '%') AND :category = 'content') OR
        (TO_CHAR(POST_REG_DATE, 'YYYY-MM-DD') LIKE '%' || :keyword || '%' AND :category = 'regDate') OR
        (POST_IS_HIDDEN = :keyword AND :category = 'isHidden') OR
        (POST_IS_REPORTED = :keyword AND :category = 'isReported')
    """, nativeQuery = true)
    int countSearchResults(
            @Param("category") String category,
            @Param("keyword") String keyword
    );



}
