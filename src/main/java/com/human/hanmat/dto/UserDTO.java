package com.human.hanmat.dto;

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
}
