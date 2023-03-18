package com.gcs.monolith.config;

import com.gcs.monolith.socket.messagequeue.mavlink.serialization.MavlinkDeserializer;
import com.MAVLink.Messages.MAVLinkMessage;
import com.gcs.monolith.socket.messagequeue.mavlink.serialization.MavlinkSerializer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.IntegerDeserializer;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListenerConfigurer;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerEndpointRegistrar;
import org.springframework.kafka.core.*;

import java.util.HashMap;
import java.util.Map;

import static com.gcs.monolith.constants.KafkaConfigurationConstants.BOOTSTRAP_SERVER;
import static com.gcs.monolith.constants.KafkaConfigurationConstants.CONSUMER_GROUP;

@EnableKafka
@Configuration
public class KafkaConfiguration implements KafkaListenerConfigurer {
    @Bean
    public ConsumerFactory<Integer, MAVLinkMessage> consumerFactory(){
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVER);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, IntegerDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, MavlinkDeserializer.class);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, CONSUMER_GROUP);
        return new DefaultKafkaConsumerFactory<>(props);
    }

    @Bean
    public ProducerFactory<Integer, MAVLinkMessage> producerFactory(){
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVER);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, MavlinkSerializer.class);
        return new DefaultKafkaProducerFactory<>(props);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<Integer, MAVLinkMessage> mavlinkListenerContainerFactory(
            ConsumerFactory<Integer, MAVLinkMessage> consumerFactory
    ) {
        ConcurrentKafkaListenerContainerFactory<Integer, MAVLinkMessage> kafkaListenerContainerFactory
                = new ConcurrentKafkaListenerContainerFactory<>();
        kafkaListenerContainerFactory.setConsumerFactory(consumerFactory);

        return kafkaListenerContainerFactory;
    }

    @Bean
    public KafkaTemplate<Integer, MAVLinkMessage> kafkaTemplate(){
        return new KafkaTemplate<>(producerFactory());
    }


    @Override
    public void configureKafkaListeners(KafkaListenerEndpointRegistrar registrar) {
        //TODO
    }
}
