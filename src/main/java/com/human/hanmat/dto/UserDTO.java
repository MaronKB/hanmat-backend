package com.human.hanmat.dto;

import com.human.hanmat.entity.User;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.query.sql.internal.ParameterRecognizerImpl;

@Data
@NoArgsConstructor
public class UserDTO {
    private String email;
    private String name;
    private String picture;
    private int radius;
    private boolean isDeleted;
    private boolean isAdmin;
    private long[] interested;
    private String description;

    @Builder
    public UserDTO(String email, String name, String picture, int radius, boolean isDeleted, boolean isAdmin, long[] interested, String description) {
        this.email = email;
        this.name = name;
        this.picture = picture;
        this.radius = radius;
        this.isDeleted = isDeleted;
        this.isAdmin = isAdmin;
        this.interested = interested;
        this.description = description;
    }

    public UserDTO(User user) {
        this.email = user.getEmail();
        this.name = user.getNickname();
        this.picture = user.getProfileImage();
        this.radius = user.getRadius();
        this.isDeleted = user.getIsDeleted().equalsIgnoreCase("Y");
        this.isAdmin = user.getIsAdmin().equalsIgnoreCase("Y");
        String[] interested = user.getInterested().split(",");
        this.interested = new long[interested.length];
        for (int i = 0; i < interested.length; i++) {
            this.interested[i] = Long.parseLong(interested[i]);
        }
        this.description = user.getDescription();
    }
}
