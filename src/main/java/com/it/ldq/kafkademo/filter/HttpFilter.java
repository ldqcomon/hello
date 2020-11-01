package com.it.ldq.kafkademo.filter;

import com.it.ldq.kafkademo.threadLocal.ThreadLLocalHoter;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @Auther: ldq
 * @Date: 2020/3/11
 * @Description:
 * @Version: 1.0
 */
@Slf4j
public class HttpFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        log.info("do filter,{},{}",Thread.currentThread().getId(),httpRequest.getServletPath());
        ThreadLLocalHoter.set(Thread.currentThread().getId());
        chain.doFilter(request,response);
    }

    @Override
    public void destroy() {

    }
}
