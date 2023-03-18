package com.gcs.supporter.domain.user.dto;

import com.gcs.domain.user.constants.Role;
import com.gcs.supporter.domain.user.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String name;
    private String username;
    private Role roles;

    public static UserDto of(UserEntity user){
        return new UserDto(
            user.getId(),
            user.getName(),
            user.getUsername(),
            user.getRoles()
        );
    }
}
