package com.human.hanmat.repository;

import com.human.hanmat.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    @Query(value = "SELECT * FROM (SELECT ROWNUM RN, T_POST.* FROM HANMAT.T_POST ORDER BY ?3 DESC) WHERE RN >= ?1 AND RN <= ?2", nativeQuery = true)
    List<Post> findAllByOrderByDesc(int start, int end, String sort);

    @Query(value = "SELECT * FROM (SELECT ROWNUM RN, T_POST.* FROM HANMAT.T_POST WHERE (POST_REG_BY = ?4 OR POST_AUTHOR = ?4) ORDER BY ?3 DESC) WHERE RN >= ?1 AND RN <= ?2", nativeQuery = true)
    List<Post> findAllByEmailOrderByDesc(int start, int end, String sort, String email);

    // 1. 특정 사용자가 작성한 리뷰 조회
    List<Post> findByRegBy(String regBy);
}
