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
        // payload에 있는 값들은 @RequestBody로 받아야 함. Json 형태의 HTTP Body를 자바 객체로 변환
        System.out.println(name);
        System.out.println(test.toString());
        return "success";
    }
}