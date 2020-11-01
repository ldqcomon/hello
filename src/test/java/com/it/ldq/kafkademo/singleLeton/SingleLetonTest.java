package com.it.ldq.kafkademo.singleLeton;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @Auther: ldq
 * @Date: 2020/3/10
 * @Description:
 * @Version: 1.0
 */
@Slf4j
public class SingleLetonTest {//懒汉式创建单实例对象
   // private static volatile SingleLetonTest instance = null; //加上 volatile 可以防止jvm的指令重排
    private SingleLetonTest() {
    }
//    public static SingleLetonTest getInstance() {
//        if (instance == null) { //双重检测
//           synchronized (SingleLetonTest.class) {//枷锁
//                if (null == instance) {
//                    instance = new SingleLetonTest();
//                }
//
//           }
//        }
//        return instance;
//    }

    public static SingleLetonTest getsinggle() {
        return SingleInstance.INSTANCES.getsingleLetonTest();
    }

    private  enum SingleInstance {
        INSTANCES;
        private SingleLetonTest instance = null;
        SingleInstance(){
            instance = new SingleLetonTest();
        }

        public SingleLetonTest getsingleLetonTest() {
            return instance;
        }
    }

    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        SingleLetonTest singleton = SingleLetonTest.getsinggle();
        Constructor<SingleLetonTest> constructor = SingleLetonTest.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        SingleLetonTest newSingleton = constructor.newInstance();
        System.out.println(singleton == newSingleton);
    }
}
