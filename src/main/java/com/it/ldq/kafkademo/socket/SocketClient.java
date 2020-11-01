package com.it.ldq.kafkademo.socket;

import java.io.*;
import java.net.Socket;

/**
 * @Auther: ldq
 * @Date: 2020/3/4
 * @Description:
 * @Version: 1.0
 */
public class SocketClient {

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1",9999);
        //网络中的流
        OutputStream os = socket.getOutputStream();
        BufferedOutputStream bos = new BufferedOutputStream(os);
        int b;
        //本地流templates/mv.jpg D:\Java_project\kafka-demo\src\main\resources\templates\mv.jpg
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(".\\src\\main\\resources\\mv.jpg"));
        while((b = bis.read())!=-1){
            //通过网络将文件写入到服务器中
            bos.write(b);
        }
        //告诉服务器文件已经传输完毕
        socket.shutdownOutput();
        InputStream is = socket.getInputStream();
        BufferedReader re = new BufferedReader(new InputStreamReader(is));
        String line;
        while ((line=re.readLine())!=null) {
            System.out.println(line);
        }

        //关闭流
        bis.close();
        socket.close();

    }

}
