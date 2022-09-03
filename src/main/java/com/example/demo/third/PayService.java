package com.example.demo.third;

import lombok.Setter;
import org.springframework.stereotype.Service;

@Service
@Setter
public class PayService {
    private Pay pay;
    public String pay() {
        return pay.pay();
    }
}
