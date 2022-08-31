package com.example.demo.third;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Objects;

@Controller
@RequiredArgsConstructor
public class ThirdController {
    private final PayService payService;
    private final ApplicationContext context;
    @ResponseBody
    @GetMapping(value = {"third/{paySelect}", "third"})
    public String callThird(@PathVariable(required = false) String paySelect) {
        paySelect = Objects.requireNonNullElse(paySelect, "SamsungPay");
        if (!context.containsBeanDefinition(paySelect)) {
            paySelect = "SamsungPay";
        }
        Pay pay = context.getBean(paySelect, Pay.class);
        payService.setPay(pay);
        return payService.pay();
    }
}
