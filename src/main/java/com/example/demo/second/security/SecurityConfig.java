package com.example.demo.second.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@RequiredArgsConstructor
//@EnableWebSecurity(debug = true)
public class SecurityConfig {

    private final JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                // UsernamePasswordAuthenticationFilter에서 인증을 하는 것 같으니까
                // jwtFilter에서 Authorization Header에 값이 login이면 위의 필터에 넘겨줘서 db 값 비교하게 하고
                // token이 정상적이면 db 값 비교없이 넘어가게 하고
                // 비정상이면 jwtFilter에서 거른다.
//                    .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                    .authorizeRequests()
                    .antMatchers("/second/login", "/second/signup").permitAll()
                    .antMatchers("/second/*").authenticated()
                .and()
                    .formLogin().disable();
//                    .loginPage("/second/login")
//                    .defaultSuccessUrl("/second/loginSuccess", true);

        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.addAllowedOrigin("*");
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}