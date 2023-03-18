package com.gcs.monolith.socket.coordinate.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CoordinateRequest {
    private Double lat;
    private Double lng;
    private Double alt;

    @Override
    public String toString() {
        return "CoordinateRequest{" +
                "lat=" + lat +
                ", lng=" + lng +
                ", alt=" + alt +
                '}';
    }
}
