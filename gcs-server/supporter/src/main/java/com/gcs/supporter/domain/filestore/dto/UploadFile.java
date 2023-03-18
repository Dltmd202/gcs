package com.gcs.supporter.domain.filestore.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UploadFile {
    private String uploadFileName;
    private String storeFileName;
}
