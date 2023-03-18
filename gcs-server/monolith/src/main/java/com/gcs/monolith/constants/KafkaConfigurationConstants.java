package com.gcs.monolith.constants;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.util.Objects;

import static java.lang.Integer.parseInt;

@Configuration
public class KafkaConfigurationConstants {
    public static String BOOTSTRAP_SERVER = "";
    public static String SERVER_TO_AGENT_TOPIC = "";
    public static String AGENT_TO_SERVER_TOPIC = "";
    public static String CONSUMER_GROUP = "";
    public static int KAFKA_POLLING_PERIOD = 0;

    public KafkaConfigurationConstants(Environment env){
        BOOTSTRAP_SERVER = env.getProperty("spring.kafka.bootstrap-servers");
        SERVER_TO_AGENT_TOPIC = env.getProperty("gcs.kafka.topics.serverToAgent");
        AGENT_TO_SERVER_TOPIC = env.getProperty("gcs.kafka.topics.agentToServer");
        KAFKA_POLLING_PERIOD = parseInt(Objects.requireNonNull(env.getProperty("gcs.kafka.polling.period")));
        CONSUMER_GROUP = env.getProperty("gcs.kafka.consumer.group");
    }
}
