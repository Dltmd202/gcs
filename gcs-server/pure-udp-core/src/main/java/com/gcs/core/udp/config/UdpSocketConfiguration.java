package com.gcs.core.udp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.DatagramSocket;
import java.net.SocketException;

@Configuration
public class UdpSocketConfiguration {

//    @Bean
//    public DatagramSocket publisherSocket(){
//        DatagramSocket socket;
//        try {
//            socket = new DatagramSocket();
//        } catch (SocketException e) {
//            throw new RuntimeException(e);
//        }
//        return socket;
//    }
}
