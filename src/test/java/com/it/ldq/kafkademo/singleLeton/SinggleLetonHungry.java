package com.it.ldq.kafkademo.singleLeton;

/**
 * @Auther: ldq
 * @Date: 2020/3/10
 * @Description:
 * @Version: 1.0
 */
public class SinggleLetonHungry {
    //懒汉式创建单实例对象,当类一旦加载就会创建该类的实例对象  属于线程安全的类
    //但是有个缺点就是 会浪费内存空间 不管你用还是不用都已经创建好了
    private static SinggleLetonHungry instance = new SinggleLetonHungry();
    private SinggleLetonHungry() {};
    public static SinggleLetonHungry getInstance() {
        return instance;
    }

}
