package com.example.demo.second;

import com.example.demo.second.security.JwtService;
import com.example.demo.second.security.UserAuthentication;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UsersMapper usersMapper;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public int signup(CustomUser customUser) {
        if (customUser.getRole() == null) {
            customUser.setRole("user");
        }
        customUser.setPassword(passwordEncoder.encode(customUser.getPassword()));
        return usersMapper.insertUsers(customUser);
    }

    public String login(CustomUser customUser) {
        CustomUser dbUser = usersMapper.selectUsersByUsername(customUser.getUsername());
        if (passwordEncoder.matches(customUser.getPassword(), dbUser.getPassword())) {
            // 세션 등록
            HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
            UserAuthentication authentication = new UserAuthentication(dbUser.getUsername(), null, null);
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authentication);

            return jwtService.generateToken(dbUser.getUsername(), dbUser.getRole());
        }
        return "Invalid Login";
    }
}
