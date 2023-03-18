package com.gcs.domain.user.dto;

import com.gcs.domain.user.User;
import com.gcs.domain.user.constants.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserDto implements User {
    private Long id;
    private String name;
    private String username;
    private Role roles;
}
