package com.gcs.monolith.constants;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

/**
 * @author Dltmd202
 */
@Getter
@ConstructorBinding
@ConfigurationProperties(prefix = "google-map")
public class GoogleMapApiConstants {
    private final String key;

    public GoogleMapApiConstants(String key) {
        this.key = key;
    }
}
