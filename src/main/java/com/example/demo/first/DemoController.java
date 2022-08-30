package com.example.demo.first;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DemoController {

    @GetMapping("/mvc")
    public String mvcPage() {
        return "first/mvcPage";
    }

}
