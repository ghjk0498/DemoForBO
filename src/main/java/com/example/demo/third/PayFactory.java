package com.example.demo.third;

public class PayFactory {
    public static Pay getPay() {
        return new SamsungPay();
    }
}
