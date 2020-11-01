package com.it.ldq.kafkademo.socket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * @Auther: ldq
 * @Date: 2020/3/4
 * @Description:
 * @Version: 1.0
 */
public class UdpReceived {

    public static void main(String[] args) throws IOException {
        DatagramSocket s = new DatagramSocket(9091);
        while (true) {
        byte[] bytes = new byte[1024];
        DatagramPacket packet = new DatagramPacket(bytes,bytes.length);
        s.receive(packet);
        byte[] data = packet.getData();
        System.out.println(new String(data,0,packet.getLength()));
        }

    }
}
