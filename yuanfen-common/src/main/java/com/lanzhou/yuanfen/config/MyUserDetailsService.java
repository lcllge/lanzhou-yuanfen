package com.lanzhou.yuanfen.config;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lanzhou.yuanfen.sys.entity.User;
import com.lanzhou.yuanfen.sys.mapper.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author LanZhou
 */
@Configuration
public class MyUserDetailsService implements UserDetailsService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private RolePermissionMapper rolePermissionMapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        List<User> users = userMapper.selectList(new QueryWrapper<User>().eq("username", s));
        if (users.size() < 1) {
            throw new RuntimeException("暂无当前用户!!!");
        }
        if (users.size() > 1) {
            throw new RuntimeException("系统用户出错, 请联系管理员清楚用户 : " + s + "!!!");
        }
        return getUserDetailsByUser(users.get(0));
    }

    private UserDetails getUserDetailsByUser(User user) {
        Set<SimpleGrantedAuthority> grantedAuthoritySet = new HashSet<>();
        List<String> perms = rolePermissionMapper.getPermByUserKey(user.getUserKey());
        for (String perm : perms) {
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(perm);
            grantedAuthoritySet.add(authority);
        }
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .authorities(grantedAuthoritySet).build();
    }


}
