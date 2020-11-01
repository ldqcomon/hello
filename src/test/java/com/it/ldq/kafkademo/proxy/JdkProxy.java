package com.it.ldq.kafkademo.proxy;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Auther: ldq
 * @Date: 2020/3/16
 * @Description:
 * @Version: 1.0
 */
@Slf4j
public class JdkProxy {//jdk动态代理演示
    //接口
    interface Car {
        void run(int speed);
        void stop();
    }
    // 被代理的类
    static class CarImpl implements Car {
        @Override
        public void run(int speed) {
            log.info("宝马以"+speed+"速度开车了....");
        }

        @Override
        public void stop() {
            log.info("宝马停车了....");
        }
    }
    //代理类(装饰类)
    static class ProxyCar implements InvocationHandler {
        private Object object;
        public Object getProxy(Object object) {
            this.object = object;
            return Proxy.newProxyInstance(object.getClass().getClassLoader(), object.getClass().getInterfaces(), this);
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            this.doBefore();
            Object result = method.invoke(object, args);
            this.doAfter();
            return result;
        }
        
        public void doBefore() {
            log.info("前置增强.........");
        }
        public void doAfter() {
            log.info("后置增强.........");
        }
    }

    public static void main(String[] args) {
        Car car = new CarImpl();
        Car proxyCar = (Car) new ProxyCar().getProxy(car);
        proxyCar.run(100);
        //proxyCar.stop();

    }

}
