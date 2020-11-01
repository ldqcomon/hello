package com.it.ldq.kafkademo.proxy;

import lombok.extern.slf4j.Slf4j;

/**
 * @Auther: ldq
 * @Date: 2020/3/16
 * @Description:
 * @Version: 1.0
 */
@Slf4j
public class StaticProxy {//静态代理演示
    //接口
    interface Car {
        void run();
    }
    // 被代理的类
    static class CarImpl implements Car {
        @Override
        public void run() {
            log.info("宝马开车了....");
        }
    }
    //代理类(装饰类)
    static class ProxyCar implements Car{
        private CarImpl car;
       public ProxyCar(Car car) {
            this.car = (CarImpl) car;
        }
        @Override
        public void run() {
           log.info("在方法的前面加上了日志.......");
            car.run();
            log.info("在方法的后面加上了日志.......");
        }
    }

    public static void main(String[] args) {
        Car car = new CarImpl();
        ProxyCar proxyCar = new ProxyCar(car);
        proxyCar.run();
    }

}
