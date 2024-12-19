package com.human.hanmat.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginDTO {
    private String id;
    private String password;

    @Builder
    public LoginDTO(String id, String password) {
        this.id = id;
        this.password = password;
    }
}
