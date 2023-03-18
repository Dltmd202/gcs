package com.gcs.supporter.domain.security;

import lombok.Getter;

@Getter
public class JwtAuthentication {
    private final Long id;
    private final String token;

    public JwtAuthentication(Long id, String token){
        this.id = id;
        this.token = token;
    }
}
