package com.gcs.domain.coordinate.llh;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class LlhCoordinate implements LlhLocatable{
    private float lat;
    private float lng;
    private float alt;

    @Builder
    public LlhCoordinate(float lat, float lng, float alt) {
        this.lat = lat;
        this.lng = lng;
        this.alt = alt;
    }
}
