package com.gcs.core.kafka.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Setter @Getter
@ConfigurationProperties(prefix = "gcs.address")
public class AgentConfiguration {
    public Map<Integer, Address> agents;

    @Getter @Setter
    public static class Address {
        public String ip;
        public Integer port;
    }
}
