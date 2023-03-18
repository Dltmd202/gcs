package com.gcs.monolith.constants;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

/**
 * @author Dltmd202
 */
@Getter
@ConstructorBinding
@ConfigurationProperties(prefix = "coordinate.initial")
public class InitialCoordinateConstants {
    private final Double lat;
    private final Double lng;
    private final Double alt;

    public InitialCoordinateConstants(double lat, double lng, double alt) {
        this.lat = lat;
        this.lng = lng;
        this.alt = alt;
    }
}
