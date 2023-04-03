package com.gcs.api.config;

import com.gcs.supporter.domain.filestore.FileStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public FileStore fileStore(){
        return new FileStore();
    }
}
