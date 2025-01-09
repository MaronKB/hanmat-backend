package com.human.hanmat.repository;

import com.human.hanmat.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long>  {
    // 1. 특정 사용자가 작성한 리뷰 조회
    List<Post> findByRegBy(String regBy);
}
