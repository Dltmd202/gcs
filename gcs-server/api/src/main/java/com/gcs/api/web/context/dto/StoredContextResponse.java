package com.gcs.api.web.context.dto;

import com.gcs.supporter.domain.context.entity.AgentContextEntity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class StoredContextResponse {
    private Long contextId;
    private String filename;
    private LocalDateTime uploadedAt;

    public StoredContextResponse(AgentContextEntity scenario){
        this.contextId = scenario.getId();
        this.filename = scenario.getUploadFileName();
        this.uploadedAt = scenario.getCreatedAt();
    }
}
