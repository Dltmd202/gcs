package com.gcs.api.web.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class UserTokenResponse {
    private final Long id;
    private final String token;
    private final String roles;
}
