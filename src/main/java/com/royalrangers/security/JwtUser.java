package com.royalrangers.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.Date;

public class JwtUser implements UserDetails {

    private Long id;
    private String username;
    private String firstname;
    private String lastname;
    private String password;
    private String email;
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

    public String getEmail() {
        return email;
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

    public static Builder newBuilder() {
        return new JwtUser().new Builder();
    }

    public class Builder {

        private Builder() {}

        public Builder setId(Long id) {
            JwtUser.this.id = id;
            return this;
        }

        public Builder setUsername(String username) {
            JwtUser.this.username = username;
            return this;
        }
        public Builder setFirstname(String firstname) {
            JwtUser.this.firstname = firstname;
            return this;
        }
        public Builder setLastname(String lastname) {
            JwtUser.this.lastname = lastname;
            return this;
        }
        public Builder setPassword(String password) {
            JwtUser.this.password = password;
            return this;
        }
        public Builder setEmail(String email) {
            JwtUser.this.email = email;
            return this;
        }
        public Builder setAuthorities(Collection<? extends GrantedAuthority> authorities) {
            JwtUser.this.authorities = authorities;
            return this;
        }

        public Builder setEnabled(boolean enabled) {
            JwtUser.this.enabled = enabled;
            return this;
        }

        public Builder setLastPasswordResetDate(Date lastPasswordResetDate) {
            JwtUser.this.lastPasswordResetDate = lastPasswordResetDate;
            return this;
        }

        public JwtUser build() {
            return JwtUser.this;
        }
    }

}
