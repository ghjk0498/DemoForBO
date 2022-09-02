package com.example.demo.second;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class CustomUser {
    private String username;
    private String password;
    private String role;
}
