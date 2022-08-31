package com.example.demo.first;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class DemoController {

    @RequestMapping("/mvc")
    public String mvcPage() {
        return "first/mvcPage";
    }

}
