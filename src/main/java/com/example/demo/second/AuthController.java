package com.example.demo.second;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class AuthController {
    private final JWTService jwtService;
    private final UsersMapper usersMapper;
    @GetMapping("login")
    public String loginPage() {
        return "second/loginPage";
    }
    @PostMapping("login")
    @ResponseBody
    public ResponseEntity<String> login(@RequestParam String username, @RequestParam String pw) {
//        if (usersMapper.selectUsersById(username) != null) {
//            ResponseCookie responseCookie = ResponseCookie.from("refreshToken", jwtService.createRefreshToken(username))
//                    .httpOnly(true)
//                    .secure(true)
//                    .path("/")
//                    .maxAge(60)
//                    .build();
//            return ResponseEntity.ok()
//                    .header(HttpHeaders.SET_COOKIE, responseCookie.toString())
//                    .body(jwtService.createAccessToken(username));
//        } else {
//            return ResponseEntity.badRequest().body("login fail");
//        }
        return null;
    }
}
