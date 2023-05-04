package com.gcs.api.web.agent.led;

import lombok.Getter;

@Getter
public class LedResponse {
    private Integer r;
    private Integer g;
    private Integer b;

    public LedResponse(Integer r, Integer g, Integer b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }
}
