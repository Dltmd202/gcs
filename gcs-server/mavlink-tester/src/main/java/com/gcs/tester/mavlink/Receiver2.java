package com.gcs.tester.mavlink;

import com.MAVLink.Messages.MAVLinkMessage;
import com.MAVLink.Parser;
import com.gcs.tester.mavlink.message.MAVLinkUtils;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.*;
import java.util.Arrays;

public class Receiver2 {
    public static void main(String[] args) throws UnknownHostException, SocketException {
        int[] data = new int[]{
                0xb9,
                0x00,
                0x00,
                0x67,
                0xa6,
                0x3e,
                0x45,
                0x00,
                0x00,
                0x00,
                0x00,
                0x00,
                0x00,
                0x00,
                0x00,
                0x00,
                0x00,
                0x00,
                0x00,
                0x60,
                0x70,
                0xa6
        };

        int length = data.length * 4;
        byte[] bytes = new byte[length];

        int index = 0;
        for (int i = 0; i < data.length; i++) {
            bytes[index++] = (byte) ((data[i] >> 24) & 0xFF);
            bytes[index++] = (byte) ((data[i] >> 16) & 0xFF);
            bytes[index++] = (byte) ((data[i] >> 8) & 0xFF);
            bytes[index++] = (byte) (data[i] & 0xFF);
        }

        System.out.println(Arrays.toString(bytes));


        MAVLinkMessage mavLinkMessage = MAVLinkUtils.getMessage(bytes, length)
                .orElseThrow(() -> new IllegalArgumentException());

        System.out.println(mavLinkMessage);

    }
}
