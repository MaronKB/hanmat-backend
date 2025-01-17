package com.human.hanmat.repository;

import com.human.hanmat.entity.Best;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BestRepository extends JpaRepository<Best, Long> {
    @Query(value = "SELECT * FROM T_BEST WHERE best_post_id = ?1", nativeQuery = true)
    Best findByBestId(int bestId);

    @Query(value = "SELECT COUNT(*) FROM T_BEST WHERE BEST_IS_VISIBLE = 'Y'", nativeQuery = true)
    int countAllVisible();
}
