package com.human.hanmat.dto;

import com.human.hanmat.entity.User;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.query.sql.internal.ParameterRecognizerImpl;

import java.util.Date;

@Data
@NoArgsConstructor
public class UserDTO {
    private String email;
    private String name;
    private String picture;
    private Integer radius;
    private boolean isDeleted;
    private boolean isAdmin;
    private long[] interested;
    private String description;
    private Date regDate;

    @Builder
    public UserDTO(String email, String name, String picture, Integer radius, boolean isDeleted, boolean isAdmin, long[] interested, String description, Date regDate) {
        this.email = email;
        this.name = name;
        this.picture = picture;
        this.radius = radius;
        this.isDeleted = isDeleted;
        this.isAdmin = isAdmin;
        this.interested = interested;
        this.description = description;
        this.regDate = regDate;
    }

    public UserDTO(User user) {
        this.email = user.getEmail();
        this.name = user.getNickname();
        this.picture = user.getProfileImage();
        this.radius = user.getRadius();
        this.isDeleted = user.getIsDeleted().equalsIgnoreCase("Y");
        this.isAdmin = user.getIsAdmin().equalsIgnoreCase("Y");
        this.description = user.getDescription();
        this.regDate = user.getRegDate();
        if (user.getInterested() == null) {
            this.interested = new long[0];
            return;
        }
        String[] interested = user.getInterested().split(",");
        this.interested = new long[interested.length];
        for (int i = 0; i < interested.length; i++) {
            this.interested[i] = Long.parseLong(interested[i]);
        }
    }
}
