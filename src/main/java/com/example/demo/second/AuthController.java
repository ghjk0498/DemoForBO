package com.example.demo.second;

import com.example.demo.second.security.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final UserService userService;
    private final JwtService jwtService;

    @GetMapping("/second/")
    public String secondIndex() {
        return "/second/index";
    }

    @GetMapping("/second/login")
    public String loginPage() {
        return "/second/loginPage";
    }

    @PostMapping("/second/login")
    @ResponseBody
    public String login(@RequestBody CustomUser customUser) {
        log.info(customUser.toString());
        return userService.login(customUser);
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
