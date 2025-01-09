package com.human.hanmat.repository;

import com.human.hanmat.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query(value = "SELECT * FROM HANMAT.T_COMMENT WHERE ROWNUM >= ?1 AND ROWNUM <= ?2 ORDER BY ?3", nativeQuery = true)
    List<Comment> findAllByOrderByAsc(int start, int end, String sort);
}
