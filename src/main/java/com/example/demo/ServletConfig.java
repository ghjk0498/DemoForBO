package com.example.demo;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Servlet;

@Configuration
public class ServletConfig {
    @Bean
    public ServletRegistrationBean demoServlet() {
        return new ServletRegistrationBean(new DemoServlet(), "/servlet");
    }
}
