package com.human.hanmat.service;

import com.human.hanmat.dto.RegisterDTO;
import com.human.hanmat.entity.User;
import com.human.hanmat.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User register(RegisterDTO registerDTO) {
        return null;
    }

    public User login(String id, String password) {
        return null;
    }

    public List<User> findAll() {
        System.out.println("findAll");
        System.out.println(userRepository.findAll());
        return userRepository.findAll();
    }

    public User findById(String id) {
        return null;
    }
}
