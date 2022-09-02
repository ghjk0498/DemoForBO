package com.example.demo.second.security;

import com.example.demo.second.CustomUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Slf4j
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            if (request.getHeader("Authorization") == null) {
                filterChain.doFilter(request, response);
                return;
            }
            String jwt = getJwtFromRequest(request);

            boolean validation = jwtService.validateToken(jwt);

            if (!isEmpty(jwt) && validation) {
                String username = jwtService.getUsernameFromJWT(jwt);
                UserAuthentication authentication = new UserAuthentication(username, null, null);
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request)); //기본적으로 제공한 details 세팅

                SecurityContextHolder.getContext().setAuthentication(authentication); //세션에서 계속 사용하기 위해 securityContext에 Authentication 등록
            } else {
                if (isEmpty(jwt)) {
                    request.setAttribute("unauthorization", "401 인증키 없음.");
                }
                if (validation) {
                    request.setAttribute("unauthorization", "401-001 인증키 만료.");
                }
            }
        } catch (Exception ex) {
            logger.error("Could not set user authentication in security context", ex);
        }

        filterChain.doFilter(request, response);
    }

    private boolean isEmpty(String s) {
        return s == null || s.equals("");
    }

    private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring("Bearer ".length());
        }
        return null;
    }
}
