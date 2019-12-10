package com.lanzhou.yuanfen.common;

import com.lanzhou.yuanfen.sys.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 新的系统用户表示Pojo
 *
 * @author Administrator
 */
public class SecurityUser extends User implements UserDetails {

    public SecurityUser(User user) {
        if (user != null) {
            this.setUserKey(user.getUserKey());
            this.setUsername(user.getUsername());
            this.setPassword(user.getPassword());
            this.setEmail(user.getEmail());
            this.setMobile(user.getMobile());
            this.setAvatar(user.getAvatar());
            this.setSmsCode(user.getSmsCode());
            this.setCode(user.getCode());
            this.setLastLoginTime(user.getLastLoginTime());
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        String username = this.getUsername();
        if (username != null) {
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(username);
            authorities.add(authority);
        }
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
