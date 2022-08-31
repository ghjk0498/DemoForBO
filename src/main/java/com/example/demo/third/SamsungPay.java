package com.example.demo.third;

import org.springframework.stereotype.Component;

@Component("SamsungPay")
public class SamsungPay implements Pay {
    @Override
    public String pay() {
        return "SamsungPay - pay()";
    }
}
