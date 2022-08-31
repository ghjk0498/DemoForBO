package com.example.demo.payload;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class AjaxController {
    @GetMapping("/ajax")
    public String ajaxPage(@ModelAttribute Test test) {
        System.out.println(test.toString());
        return "success";
    }
    @PostMapping("/ajax")
    public String ajaxPage(@RequestBody String name, @ModelAttribute Test test) {
        System.out.println(name);
        System.out.println(test.toString());
        return "success";
    }
}