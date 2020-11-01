package com.it.ldq.kafkademo.io;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.*;

/**
 * @Auther: ldq
 * @Date: 2020/11/1
 * @Description:
 * @Version: 1.0
 */
@Slf4j
public class IoTest {
    @Test
    public void test1() throws IOException {
        String s = "hello";
       // FileOutputStream outputStream = new FileOutputStream("d:/hello.txt");
        FileWriter fileWriter = new FileWriter("d:/hello2.txt");
       // outputStream.write(s.getBytes());
        fileWriter.write(s);
        fileWriter.close();
       // outputStream.close();

    }

    @Test
    public void test2() throws IOException {
        FileInputStream in = new FileInputStream("d:/hello.txt");
       // int i = in.read();
        int len = 0;
        byte[] buff = new byte[1024];
        while ((len=in.read(buff)) !=-1) {
            // 打印出来
            //使用String 对返回的int类型的数据进行解码
            String s = new String(buff,0,len);
            log.info("result:{}",s);
        }

    }

    @Test
    public void test3() throws IOException {
        FileInputStream is = new FileInputStream("D:\\Downloads\\ideaIU-2019.2.3.exe");
        FileOutputStream os = new FileOutputStream("D:\\Downloads\\ideaIU-2019.2.322.exe");
        BufferedInputStream bis = new BufferedInputStream(is);
        BufferedOutputStream bos = new BufferedOutputStream(os);
        int len;
        byte[] buff = new byte[1024];
        long start = System.currentTimeMillis();
        while ((len=bis.read(buff))!=-1) {
            bos.write(buff,0,len);
        }
        is.close();
        os.close();
        long end = System.currentTimeMillis();
        log.info("共花费时间:{}",(end-start)/1000+"秒");

    }
}
