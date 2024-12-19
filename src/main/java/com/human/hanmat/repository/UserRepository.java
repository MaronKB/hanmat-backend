package com.human.hanmat.repository;

import com.human.hanmat.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    User findByEmail(String email);
    User findByIdAndPassword(String id, String password);
}