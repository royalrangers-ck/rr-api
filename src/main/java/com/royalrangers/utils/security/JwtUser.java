package com.royalrangers.utils.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.Date;

public class JwtUser implements UserDetails {

    private Long id;
    private String username; //email
    private String firstname;
    private String lastname;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;
    private boolean enabled;
    private Date lastPasswordResetDate;

    private JwtUser() {}

    @JsonIgnore
    public Long getId() {
        return id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }



    @JsonIgnore
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @JsonIgnore
    public Date getLastPasswordResetDate() {
        return lastPasswordResetDate;
    }

    static Builder newBuilder() {
        return new JwtUser().new Builder();
    }

    public class Builder {

        private Builder() {}

        Builder setId(Long id) {
            JwtUser.this.id = id;
            return this;
        }

        Builder setUsername(String username) {
            JwtUser.this.username = username;
            return this;
        }
        Builder setFirstname(String firstname) {
            JwtUser.this.firstname = firstname;
            return this;
        }
        Builder setLastname(String lastname) {
            JwtUser.this.lastname = lastname;
            return this;
        }
        Builder setPassword(String password) {
            JwtUser.this.password = password;
            return this;
        }

        Builder setAuthorities(Collection<? extends GrantedAuthority> authorities) {
            JwtUser.this.authorities = authorities;
            return this;
        }

        Builder setEnabled(boolean enabled) {
            JwtUser.this.enabled = enabled;
            return this;
        }

        Builder setLastPasswordResetDate(Date lastPasswordResetDate) {
            JwtUser.this.lastPasswordResetDate = lastPasswordResetDate;
            return this;
        }

        JwtUser build() {
            return JwtUser.this;
        }
    }

}
