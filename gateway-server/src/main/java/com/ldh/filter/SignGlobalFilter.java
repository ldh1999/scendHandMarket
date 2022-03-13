package com.ldh.filter;

import com.alibaba.fastjson.JSONObject;
import com.ldh.util.SignUtil;
import common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@Order(-1)
public class SignGlobalFilter implements GlobalFilter {

    @Autowired
    private  SignUtil signUtil;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        ServerHttpRequest request = exchange.getRequest();
        HttpHeaders httpHeaders = request.getHeaders();
        try{
            String sign = httpHeaders.get("sign").get(0);
            String timeT = httpHeaders.get("timeT").get(0);
            if (!signUtil.isSuccess(sign, timeT)){
                throw new Exception();
            }
        }catch (Exception e){

            ServerHttpResponse response = exchange.getResponse();
            Result result = new Result();
            result.setCode(600);
            result.setSuccess(false);
            result.setMessage("sign error !签名验证失败");

            String dataJson = JSONObject.toJSON(result).toString();
            response.setStatusCode(HttpStatus.UNAUTHORIZED);

            byte[] datas = dataJson.getBytes();
            DataBuffer buffer = response.bufferFactory().wrap(datas);
            response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
            return response.writeWith(Mono.just(buffer));
        }
        return chain.filter(exchange);
    }
}
