package com.royalrangers.security;

import com.royalrangers.model.Authority;
import com.royalrangers.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public final class JwtUserFactory {

    private JwtUserFactory() {
    }

    public static JwtUser create(User user) {
        return JwtUser.newBuilder()
                            .setId(user.getId())
                            .setUsername(user.getUsername())
                            .setFirstname(user.getFirstName())
                            .setLastname(user.getLastName())
                            .setEmail(user.getEmail())
                            .setPassword(user.getPassword())
                            .setAuthorities(mapToGrantedAuthorities(user.getAuthorities()))
                            .setEnabled(user.getEnabled())
                            .setLastPasswordResetDate(user.getLastPasswordResetDate())
                            .build();
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(Set<Authority> authorities) {
        return authorities.stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getName().name()))
                .collect(Collectors.toList());
    }
}
