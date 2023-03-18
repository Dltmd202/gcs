package com.gcs.domain.coordinate.ned;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class NedCoordinate implements NedLocatable{
    private float x;
    private float y;
    private float z;

    @Builder
    public NedCoordinate(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
}
