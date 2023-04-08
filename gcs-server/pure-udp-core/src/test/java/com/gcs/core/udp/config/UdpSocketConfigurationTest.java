package com.gcs.core.udp.config;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.net.DatagramSocket;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("dev")
class UdpSocketConfigurationTest {

    @Autowired DatagramSocket socket;

    @Test
    @DisplayName("전송용 UDP 소켓 Spring Bean 테스트")
    void sendingSocketTest(){
        assertThat(socket).isNotNull();
        assertThat(socket).isInstanceOf(DatagramSocket.class);
    }

}