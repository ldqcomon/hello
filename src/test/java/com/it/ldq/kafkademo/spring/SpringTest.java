package com.it.ldq.kafkademo.spring;

import com.it.ldq.kafkademo.entity.Company;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Auther: ldq
 * @Date: 2020/10/11
 * @Description:
 * @Version: 1.0
 */
@Slf4j
public class SpringTest {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("p.xml");
        Company student = (Company) context.getBean("student");
        log.info("student:{}",student);
    }
}
