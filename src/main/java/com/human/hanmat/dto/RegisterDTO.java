package com.human.hanmat.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RegisterDTO {
    private String id;
    private String nickname;
    private String email;
    private String password;
    private String passwordCheck;

    @Builder
    public RegisterDTO(String id, String nickname, String email, String password, String passwordCheck) {
        this.id = id;
        this.nickname = nickname;
        this.email = email;
        this.password = password;
        this.passwordCheck = passwordCheck;
    }
}
