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

}
