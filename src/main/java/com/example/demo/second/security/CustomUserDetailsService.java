package com.example.demo.second.security;

import com.example.demo.second.CustomUser;
import com.example.demo.second.UsersMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UsersMapper usersMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        CustomUser user = usersMapper.selectUsersByUsername(username);
        System.out.println(user.toString());

        return new SecurityUser(user.getUsername(), user.getPassword(),
                Arrays.asList(new SimpleGrantedAuthority(user.getRole())));
    }

}
