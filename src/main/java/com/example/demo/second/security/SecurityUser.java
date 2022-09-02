package com.example.demo.second.security;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.List;

@Getter
@EqualsAndHashCode(callSuper = true)
public class SecurityUser extends User {

    private static final long serialVersionUID = 1L;

    public SecurityUser(String username, String password, boolean enabled, boolean accountNonExpired,
                        boolean credentialsNonExpired, boolean accountNonLocked,
                        Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired,
                accountNonLocked, authorities);
    }

    public SecurityUser(String username, String password, List<SimpleGrantedAuthority> asList) {
        this(username, password, true, true, true, true, asList);
    }

}