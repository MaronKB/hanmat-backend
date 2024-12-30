package com.human.hanmat.service;

import com.human.hanmat.dto.UserDTO;
import com.human.hanmat.entity.User;
import com.human.hanmat.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User register(UserDTO userDTO) {
        User user = User.builder()
                .email(userDTO.getEmail())
                .nickname(userDTO.getName())
                .build();
        return userRepository.save(user);
    }

    public User login(UserDTO userDTO) {
        User user = userRepository.findByEmail(userDTO.getEmail());
        if (user == null) {
            user = register(userDTO);
        }
        return user;
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
