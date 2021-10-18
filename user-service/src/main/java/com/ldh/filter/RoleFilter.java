package com.ldh.filter;

import com.alibaba.fastjson.JSONObject;
import common.Result;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.RedisTemplate;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

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
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
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
                JSONObject jsonObject = (JSONObject) JSONObject.toJSON(result);
                response.getWriter().print(jsonObject.toString());
                return;
            }
            if (redisTemplate.hasKey(token)){
                filterChain.doFilter(servletRequest,servletResponse);
            }else {
                result.error("token失效");
                JSONObject jsonObject = (JSONObject) JSONObject.toJSON(result);
                response.getWriter().print(jsonObject.toString());
                return;
            }
        }
    }
}
