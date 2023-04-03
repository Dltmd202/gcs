package com.gcs.api.web.agent.coordinate.llh.dto;

import com.gcs.domain.coordinate.llh.LlhLocatable;
import lombok.Builder;
import lombok.Getter;

@Getter
public class LlhResponse {

    public static LlhResponse defaultInstance = new LlhResponse(0.F, 0.F, 0.F);
    private Float lat;
    private Float lng;
    private Float alt;

    @Builder
    public LlhResponse(Float lat, Float lng, Float alt) {
        this.lat = lat;
        this.lng = lng;
        this.alt = alt;
    }

    public LlhResponse(LlhLocatable llh){
        this.lat = llh.getLat();
        this.lng = llh.getLng();
        this.alt = llh.getAlt();
    }

}
