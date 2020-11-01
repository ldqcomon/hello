package com.it.ldq.kafkademo.threadLocal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Auther: ldq
 * @Date: 2020/3/11
 * @Description:
 * @Version: 1.0
 */
@Controller
public class ThreadLocalController {

    @RequestMapping("/threadLocal/test")
    @ResponseBody
    public Long  getThreadLocal() {
        return ThreadLLocalHoter.get();
    }
}
