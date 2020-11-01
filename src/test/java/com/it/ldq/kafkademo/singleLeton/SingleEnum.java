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
public class SingleEnum {

    private static class SingletonHolder {
        private static SingleEnum instance = new SingleEnum();
    }

    private SingleEnum() {

    }

    public static SingleEnum getInstance() {
        return SingletonHolder.instance;
    }

    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        SingleEnum instance = SingleEnum.getInstance();
        System.out.println("内存的地址:"+instance.hashCode());
        Constructor<SingleEnum> declaredConstructor = SingleEnum.class.getDeclaredConstructor();
        declaredConstructor.setAccessible(true);
        SingleEnum singleEnum = declaredConstructor.newInstance();
        System.out.println(instance == singleEnum);

    }

}
