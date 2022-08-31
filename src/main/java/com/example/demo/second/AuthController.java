package com.example.demo.second;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class AuthController {
    // DB에 있다고 가정
    private String id = "a";
    private String pw = "123";
    private String id2 = "b";
    private String pw2 = "123";
    private final JWTService jwtService;

    @GetMapping("login")
    public String loginPage() {
        return "second/loginPage";
    }
    @PostMapping("login")
    public String login(Model model, @RequestParam String id, @RequestParam String pw) {
        if (id.equals(this.id) && pw.equals(this.pw)) {
            model.addAttribute("username", "user1");
        } else if (id.equals(this.id2) && pw.equals(this.pw2)) {
            model.addAttribute("username", "user2");
        }
        return "second/loginSuccessPage";
    }
}
