package com.human.hanmat.service;

import com.human.hanmat.dto.UserDTO;
import com.human.hanmat.entity.User;
import com.human.hanmat.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User register(UserDTO userDTO) {
        User user = User.builder()
                .email(userDTO.getEmail())
                .nickname(userDTO.getName())
                .profileImage(userDTO.getPicture())
                .regDate(new Date(System.currentTimeMillis()))
                .regBy("system@hanmat.com")
                .isDeleted("N")
                .isAdmin("N")
                .build();
        return userRepository.save(user);
    }

    public User login(UserDTO userDTO) {
        User user = userRepository.findByEmail(userDTO.getEmail());
        if (user == null) {
            user = register(userDTO);
        } else {
            user.setProfileImage(userDTO.getPicture());
            userRepository.save(user);
        }
        return user;
    }

    public List<User> findAll() {
        System.out.println("findAll");
        System.out.println(userRepository.findAll());
        return userRepository.findAll();
    }

    public List<UserDTO> getPage(int page, int size, String sort) {
        List<User> userPage = userRepository.findAllByOrderByAsc((page - 1) * size, (page) * size, sort);
        List<UserDTO> userDTOList = new ArrayList<>();
        for (User user: userPage) {
            userDTOList.add(new UserDTO(user));
        }
        return userDTOList;
    }

    public int getTotal() {
        return (int) userRepository.count();
    }

    public UserDTO findByEmail(String email) {
        User user = userRepository.findByEmail(email);
        return new UserDTO(user);
    }
}
