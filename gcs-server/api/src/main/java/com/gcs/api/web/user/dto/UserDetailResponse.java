package com.gcs.api.web.user.dto;

import com.gcs.supporter.domain.user.entity.UserEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDetailResponse {
    private String name;
    private String username;
    private String role;

    public UserDetailResponse(UserEntity user){
        this.name = user.getName();
        this.username = user.getUsername();
        this.role = user.getRoles().getValue();
    }

    public static UserDetailResponse of(UserEntity user){
        return new UserDetailResponse(user);
    }
}