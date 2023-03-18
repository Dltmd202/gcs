package com.gcs.monolith.web.user.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

// TODO 개별 드론에 대한 알림

@Getter
@Setter
public class UserSignInRequest {
    @NotBlank
    private String username;

    @NotBlank
    private String password;
}
