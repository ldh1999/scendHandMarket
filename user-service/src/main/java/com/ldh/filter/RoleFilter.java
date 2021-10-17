package com.ldh.filter;

import common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Order(value = 1)
public class RoleFilter implements Filter {

    private RedisTemplate redisTemplate;

    public RoleFilter(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public final Set<String> EXURL = new HashSet(Arrays.asList("/sys/user/login"));

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        String path = request.getRequestURI();
        boolean flag = EXURL.contains(path);
        if (flag){
            filterChain.doFilter(servletRequest,servletResponse);
        }else {
            Result<?> result = new Result<>();
            String token =(String) session.getAttribute("token");
            if(token == null){
                result.error("请登录");
                request.setAttribute("result",result);
                return;
            }
            if (redisTemplate.hasKey(token)){
                filterChain.doFilter(servletRequest,servletResponse);
            }else {
                result.error("token失效");

                request.setAttribute("result",result);
                return;
            }
        }
    }
}
