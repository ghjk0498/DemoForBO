package com.example.demo.second;

import com.example.demo.second.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final JwtService jwtService;

    @GetMapping("/second/login")
    public String login() {
        return "/second/loginPage";
    }

    @RequestMapping("/second/loginSuccess")
    public String loginSuccessPage(Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
        System.out.println(username);
//        jwtService.generateToken()
        model.addAttribute("username", username);
        return "/second/loginSuccessPage";
    }

    @GetMapping("/second/signup")
    public String signupPage() {
        return "/second/signupPage";
    }

    @PostMapping("/second/signup")
    public String signup(@ModelAttribute CustomUser customUser) {
        userService.signup(customUser);
        return "redirect:/second/login";
    }

}
