package com.gcs.api.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

/**
 * @author Dltmd202
 */
@Getter
@AllArgsConstructor
@ConstructorBinding
@ConfigurationProperties(prefix = "jwt.token")
public class JwtTokenConstants {
    private final String header;
    private final String issuer;
    private final String clientSecret;
    private int expirySeconds;
}
