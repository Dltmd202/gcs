package com.gcs.api.config;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

import java.util.Objects;

/**
 * @author Dltmd202
 */
@Getter
@ConstructorBinding
@ConfigurationProperties(prefix = "ros2.ros2-web-bridge.web-socket")
public class Ros2WebBridgeConfiguration {
    private final String schema;
    private final String ip;
    private final String port;
    private static String fullPath;

    public Ros2WebBridgeConfiguration(String schema, String ip, String port) {
        this.schema = schema;
        this.ip = ip;
        this.port = port;
    }

    // TODO 테스트
    public String getFullPath(){
        if(Objects.isNull(Ros2WebBridgeConfiguration.fullPath))
            Ros2WebBridgeConfiguration.fullPath = String.format("%s://%s:%s", schema, ip, port);
        return Ros2WebBridgeConfiguration.fullPath;
    }
}
