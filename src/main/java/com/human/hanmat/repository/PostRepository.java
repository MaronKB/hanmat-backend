package com.human.hanmat.repository;

import com.human.hanmat.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

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
    List<Post> findByRegBy(String regBy);
}
