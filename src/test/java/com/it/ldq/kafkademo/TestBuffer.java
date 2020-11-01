package com.it.ldq.kafkademo;

import org.junit.Test;
import java.nio.ByteBuffer;

/**
 * @Auther: ldq
 * @Date: 2020/3/1
 * @Description:
 * @Version: 1.0
 */

public class TestBuffer {

    @Test
    public void test2(){

        String str = "abcde";

        ByteBuffer buf = ByteBuffer.allocate(1024);

        buf.put(str.getBytes());

        buf.flip();

        byte[] dst = new byte[buf.limit()];
        ByteBuffer byteBuffer = buf.get(dst, 0, 4);//将从buff里获取到的数据放入到定义的 dst byte数组中
        System.out.println(new String(dst,0,2));//ab
        System.out.println(buf.position());//2

        //mark()：标记
        buf.mark();

        buf.get(dst,4,1);
        System.out.println(new String(dst,2,2));//cd
        System.out.println(buf.position());//4

        //reset()：恢复到mark的位置
        buf.reset();
        System.out.println(buf.position());//2

        //判断缓冲区中是否还有剩余数据
        if(buf.hasRemaining()){
            //获得缓冲区中可以操作的剩余数量
            System.out.println(buf.remaining());//3
        }

    }

    @Test
    public void test1(){

        String str = "abcde";

        //1.分配一个指定大小的缓存区
        ByteBuffer buf = ByteBuffer.allocate(1024);

        System.out.println("------allocation()------");
        System.out.println(buf.position());
        System.out.println(buf.limit());
        System.out.println(buf.capacity());

        //2.利用put()存入数据到缓存区中
        buf.put(str.getBytes());

        System.out.println("------put()------");
        System.out.println(buf.position());
        System.out.println(buf.limit());
        System.out.println(buf.capacity());

        //3.切换成读取数据的模式
        buf.flip();

        System.out.println("------filp()------");
        System.out.println(buf.position());
        System.out.println(buf.limit());
        System.out.println(buf.capacity());

        //4.利用get()方法读取缓冲区中的数据
        byte[] dst = new byte[buf.limit()];
        buf.get(dst);
        System.out.println(new String(dst,0,dst.length));

        System.out.println("------get()------");
        System.out.println(buf.position());
        System.out.println(buf.limit());
        System.out.println(buf.capacity());

        //5.rewind()：可重复读数据
        buf.rewind();
        System.out.println("------rewind()------");
        System.out.println(buf.position());
        System.out.println(buf.limit());
        System.out.println(buf.capacity());

        //6.clear()：清空缓冲区，但是缓存区的数据依然存在，但是处于被遗忘状态
        buf.clear();

        System.out.println("------clear()------");
        System.out.println(buf.position());
        System.out.println(buf.limit());
        System.out.println(buf.capacity());

    }

}
