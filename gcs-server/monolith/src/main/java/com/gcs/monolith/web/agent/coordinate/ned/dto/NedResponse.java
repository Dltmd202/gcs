package com.gcs.monolith.web.agent.coordinate.ned.dto;

import com.gcs.domain.coordinate.ned.NedLocatable;
import lombok.Builder;
import lombok.Getter;

@Getter
public class NedResponse {

    public static NedResponse defaultInstance = new NedResponse(0.F, 0.F, 0.F);
    private Float x;
    private Float y;
    private Float z;

    @Builder
    public NedResponse(Float x, Float y, Float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public NedResponse(NedLocatable ned){
        this.x = ned.getX();
        this.y = ned.getY();
        this.z = ned.getZ();
    }
}
