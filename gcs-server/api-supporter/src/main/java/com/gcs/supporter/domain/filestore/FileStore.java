package com.gcs.supporter.domain.filestore;

import com.gcs.supporter.domain.filestore.dto.UploadFile;
import com.gcs.supporter.error.exception.ApiException;
import com.gcs.supporter.error.exception.ErrorCode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

public class FileStore {
    @Value("${file.dir}")
    private String fileDir;

    public String getFileSchemaFullPath(String filename){
        return String.format("file:%s", getFullPath(filename));
    }

    public String getFullPath(String filename) {
        return String.format("%s%s", fileDir, filename);
    }

    public List<UploadFile> storeFiles(List<MultipartFile> files){
        return files.stream()
                .filter(f -> !f.isEmpty())
                .map(this::storeFile)
                .collect(Collectors.toList());
    }

    public UploadFile storeFile(MultipartFile file){
        if(Objects.isNull(file)) return null;

        String originalFilename = file.getOriginalFilename();
        String storeFileName = createStoreFileName(originalFilename);
        try {
            File newFile = new File(getFullPath(storeFileName));
            file.transferTo(newFile);
        } catch (IOException e) {
            // TODO exception 전환
            throw new ApiException(ErrorCode.FILE_NOT_STORED);
        }

        return new UploadFile(originalFilename, storeFileName);
    }

    private String createStoreFileName(String originalName){
        String ext = extractExt(originalName);
        String uuid = UUID.randomUUID().toString();
        return String.format("%s.%s", uuid, ext);
    }

    private String extractExt(String originalFilename) {
        int pos = originalFilename.lastIndexOf(".");
        return originalFilename.substring(pos + 1);
    }
}
