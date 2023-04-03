package com.gcs.api.web.agent.coordinate;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CoordinateResponse {
    private Double lat;
    private Double lng;
    private Double alt;
    private Double x;
    private Double y;
    private Double z;

    @Builder
    public CoordinateResponse(Double lat, Double lng, Double alt, Double x, Double y, Double z) {
        this.lat = lat;
        this.lng = lng;
        this.alt = alt;
        this.x = x;
        this.y = y;
        this.z = z;
    }
}
