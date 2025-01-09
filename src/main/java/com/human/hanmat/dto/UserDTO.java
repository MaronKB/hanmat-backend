package com.human.hanmat.dto;

import com.human.hanmat.entity.User;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDTO {
    private String email;
    private String name;
    private String picture;

    @Builder
    public UserDTO(String email, String name, String picture) {
        this.email = email;
        this.name = name;
        this.picture = picture;
    }

    public UserDTO(User user) {
        this.email = user.getEmail();
        this.name = user.getNickname();
        this.picture = user.getProfileImage();
    }
}
