package com.lanzhou.yuanfen.security.provider;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lanzhou.yuanfen.security.token.EmailAuthenticationToken;
import com.lanzhou.yuanfen.sys.entity.User;
import com.lanzhou.yuanfen.sys.mapper.RolePermissionMapper;
import com.lanzhou.yuanfen.sys.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Ip认证的处理者
 *
 * @author Administrator
 */
@Slf4j
public class EmailAuthenticationProvider implements AuthenticationProvider {

    @Resource
    private RolePermissionMapper rolePermissionMapper;

    @Resource
    private UserMapper userMapper;


    /**
     * 定义ip认证权限容器
     */
    final static Map<String, List<SimpleGrantedAuthority>> emailAuthorityMap = new HashMap<>();

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        EmailAuthenticationToken ipAuthenticationToken;
        if (authentication instanceof EmailAuthenticationToken) {
            ipAuthenticationToken = (EmailAuthenticationToken) authentication;
        } else {
            return null;
        }
        String email = ipAuthenticationToken.getEmail();
        String eCode = ipAuthenticationToken.geteCode();
        if (StringUtils.isEmpty(email) || email.length() == 0 || StringUtils.isEmpty(eCode) || eCode.length() == 0) {
            log.warn("参数无效, 请重新尝试获取验证码 !!!");
            return null;
        }
        User user = userMapper.selectOne(new QueryWrapper<User>().eq("email", email).eq("eml_code", eCode));
        if(user == null){
            log.warn("暂无当前用户 !!!");
            return null;
        }
        if (emailAuthorityMap.size() == 0) {
            initEmailAuthorityMap();
        }
        List<SimpleGrantedAuthority> authority = emailAuthorityMap.get(email);
        if (authority == null) {
            log.info("邮箱验证通过, 尝试登入 !!");
            return new EmailAuthenticationToken(email, eCode, new ArrayList<SimpleGrantedAuthority>());
        } else {
            log.info("邮箱验证通过, 尝试登入 !!");
            // 注意这里是认证成功后返回的IpAuthenticationToken(构造器要使用正确哟)
            return new EmailAuthenticationToken(email, eCode, authority);
        }
    }

    /**
     * 初始化Email登入数据
     */
    private void initEmailAuthorityMap() {
        List<Map<String, String>> emailPermKeyMap = rolePermissionMapper.getEmailPermMap();
        Map<String, List<Map<String, String>>> email = emailPermKeyMap.stream().collect(Collectors.groupingBy(x -> x.get("email")));
        for (Map.Entry<String, List<Map<String, String>>> listEntry : email.entrySet()) {
            String key = listEntry.getKey();
            List<Map<String, String>> value = listEntry.getValue();
            List<SimpleGrantedAuthority> grantedAuthorities = new ArrayList<>();
            for (Map<String, String> stringMap : value) {
                String code = stringMap.get("code");
                if (code != null) {
                    SimpleGrantedAuthority authority = new SimpleGrantedAuthority(code);
                    grantedAuthorities.add(authority);
                }
            }
            emailAuthorityMap.put(key, grantedAuthorities);
        }


    }

    /**
     * 只支持IpAuthenticationToken该身份
     *
     * @param authentication
     * @return
     */
    @Override
    public boolean supports(Class<?> authentication) {
        return EmailAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
