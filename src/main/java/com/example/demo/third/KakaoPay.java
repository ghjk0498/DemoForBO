package com.example.demo.third;

import org.springframework.stereotype.Component;

@Component("KakaoPay")
public class KakaoPay implements Pay {
    @Override
    public String pay() {
        return "KakaoPay - pay()";
    }
}
