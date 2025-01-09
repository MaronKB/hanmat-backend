package com.human.hanmat.repository;

import com.human.hanmat.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    @Query(value = "SELECT * FROM HANMAT.T_POST WHERE ROWNUM >= ?1 AND ROWNUM <= ?2 ORDER BY ?3", nativeQuery = true)
    List<Post> findAllByOrderByAsc(int start, int end, String sort);
}
