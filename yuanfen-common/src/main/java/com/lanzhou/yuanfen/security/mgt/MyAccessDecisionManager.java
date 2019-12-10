package com.lanzhou.yuanfen.security.mgt;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Iterator;

/**
 * @author LanZhou
 */
@Configuration
public class MyAccessDecisionManager implements AccessDecisionManager {
    /**
     * decide 方法是判定是否拥有权限的决策方法，authentication是CustomUserService
     * 中循环添加到 GrantedAuthority 对象中的权限信息集合,object 包含客户端发起的请求的request信息，
     * 可转换为 HttpServletRequest request = ((FilterInvocation) object).getHttpRequest();
     * configAttributes为MyFilterInvocationSecurityMetadataSource的getAttributes(Object object)
     * 这个方法返回的结果.
     */
    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
        if (null == configAttributes || configAttributes.size() <= 0) {
            return;
        }
        ConfigAttribute c;
        String needRole;
        for (Iterator<ConfigAttribute> iter = configAttributes.iterator(); iter.hasNext(); ) {
            c = iter.next();
            needRole = c.getAttribute();
            //authentication 为在注释1 中循环添加到 GrantedAuthority 对象中的权限信息集合
            for (GrantedAuthority ga : authentication.getAuthorities()) {
                if (needRole.trim().equals(ga.getAuthority())) {
                    return;
                }
            }
        }
        throw new AccessDeniedException("no right");
    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }

}
