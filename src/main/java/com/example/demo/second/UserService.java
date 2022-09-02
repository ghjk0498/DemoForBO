package com.example.demo.second;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UsersMapper usersMapper;
    private final BCryptPasswordEncoder passwordEncoder;

    public int signup(CustomUser customUser) {
        if (customUser.getRole() == null) {
            customUser.setRole("user");
        }
        customUser.setPassword(passwordEncoder.encode(customUser.getPassword()));
        return usersMapper.insertUsers(customUser);
    }

}
