package com.ldh.constant;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component("signConstant")
public class SignConstant {
    @Value("${signN.ls}")
    private Float ls;
    @Value("${signN.uup}")
    private Float uup;
}
