package com.it.ldq.kafkademo.jvm;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: ldq
 * @Date: 2020/3/6
 * @Description:
 * @Version: 1.0
 */
public class Softreference {
    public static void main(String[] args) {
        List<SoftReference<byte[]>> list = new ArrayList<>();
        //软引用的队列
        ReferenceQueue<byte[]> referenceQueue = new ReferenceQueue<>();
        for (int i = 0; i < 5; i++) {
            //关联 软引用队列,当软引用对象所关联的byte[]被回收时候,该软引用对象会被加入到软引用队列中去
            SoftReference<byte[]> softReference = new SoftReference<>(new byte[1024*4*1024],referenceQueue);
                System.out.println(softReference.get());
                list.add(softReference);
                System.out.println(list.size());
        }
        Reference<? extends byte[]> poll = referenceQueue.poll();
        while(null !=poll) {
            list.remove(poll);
            poll= referenceQueue.poll();
        }

        for (SoftReference<byte[]> s:list) {
            System.out.println(s.get());
        }
    }
}
