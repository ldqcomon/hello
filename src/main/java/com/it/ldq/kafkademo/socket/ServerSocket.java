package com.it.ldq.kafkademo.socket;

import java.io.*;
import java.net.Socket;

/**
 * @Auther: ldq
 * @Date: 2020/3/4
 * @Description:
 * @Version: 1.0
 */
public class ServerSocket {
    public static void main(String[] args) throws IOException {
        java.net.ServerSocket serverSocket = new java.net.ServerSocket(9999);
        System.out.println("start connecting...........");
        Socket accept = serverSocket.accept();
        System.out.println("started connect...........");
        //会一直阻塞 一直到有客户端连接
        InputStream is = accept.getInputStream();
        //网络中的流
        BufferedInputStream bis = new BufferedInputStream(is);
        //本地中的流
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(".\\src\\main\\resources\\mv1.jpg"));
        int len;
        while ((len = bis.read())!=-1) {
            bos.write(len);
        }
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(accept.getOutputStream()));
        writer.write("长传成功!");
        writer.newLine();
        writer.flush();
        //关闭流
        bos.close();
        accept.close();
        serverSocket.close();
    }
}
