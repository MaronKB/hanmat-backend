package com.human.hanmat.repository;

import com.human.hanmat.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    User findByEmail(String email);

    @Query(value = "SELECT * FROM (SELECT ROWNUM RN, T_USER.* FROM HANMAT.T_USER ORDER BY ?3) WHERE RN BETWEEN ?1 AND ?2", nativeQuery = true)
    List<User> findAllByOrderByAsc(int page, int size, String sort);
}