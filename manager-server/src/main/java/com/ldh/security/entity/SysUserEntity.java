package com.ldh.security.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@Data
public class SysUserEntity implements Serializable, UserDetails {

    private String userId;
    private String token;
    private String username;
    private String password;
    private String sts;
    private List<GrantedAuthority> roles;


    public SysUserEntity() {
    }

    public SysUserEntity(String userId, String token, String username, String password, String sts, List<GrantedAuthority> roles) {
        this.userId = userId;
        this.token = token;
        this.username = username;
        this.password = password;
        this.sts = sts;
        this.roles = roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
