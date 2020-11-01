package com.it.ldq.kafkademo.socket;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;

/**
 * @Auther: ldq
 * @Date: 2020/3/4
 * @Description:
 * @Version: 1.0
 */
public class Udpsend {

    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket();
            Scanner scanner = new Scanner(System.in);
            //String msg = "hello world!!";
            while(true) {
                if ("886".equals(scanner.nextLine())){
                    break;
                }
            byte[] msgBytes = scanner.nextLine().getBytes();
            DatagramPacket packet = new DatagramPacket(msgBytes,msgBytes.length,InetAddress.getByName("127.0.0.1"),9091);
            //发送数据包
            socket.send(packet);
            }
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
