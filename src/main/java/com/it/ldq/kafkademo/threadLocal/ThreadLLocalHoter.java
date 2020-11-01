package com.it.ldq.kafkademo.threadLocal;

/**
 * @Auther: ldq
 * @Date: 2020/3/11
 * @Description:
 * @Version: 1.0
 */
public class ThreadLLocalHoter {
   private static ThreadLocal threadLocal = new ThreadLocal();
    public static void set(long a) {
        threadLocal.set(a);
    }
    public static long get() {
        return (long) threadLocal.get();
    }
    public static void remove() {
        threadLocal.remove();
    }
}
