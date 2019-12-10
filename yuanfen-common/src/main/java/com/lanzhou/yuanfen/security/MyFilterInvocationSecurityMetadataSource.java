package com.lanzhou.yuanfen.security;

import com.lanzhou.yuanfen.sys.entity.Permission;
import com.lanzhou.yuanfen.sys.mapper.PermissionMapper;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 用于过滤器和权限的对应关系加载和决断
 * @author LanZhou
 */
@Configuration
public class MyFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    @Resource
    private PermissionMapper permissionMapper;

    private HashMap<String, Collection<ConfigAttribute>> map = null;

    /**
     * 加载权限表中所有权限
     * 表示当前路径:权限 对应的List
     */
    public void loadResourceDefine() {
        map = new HashMap<>();
        List<Permission> permissions = permissionMapper.selectList(null);
        for (Permission permission : permissions) {
            ConfigAttribute cfg = new SecurityConfig(permission.getCode());
            List<ConfigAttribute> list = new ArrayList<>();
            list.add(cfg);
            map.put(permission.getUrl(), list);
        }
    }

    /**
     * 此方法是为了判定用户请求的url 是否在权限表中，如果在权限表中，则返回给 decide 方法， 用来判定用户
     * 是否有此权限。如果不在权限表中则放行。
     */
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        if (map == null) {
            loadResourceDefine();
        }
        Collection<ConfigAttribute> configAttributes = new ArrayList<>();
        // object 中包含用户请求的request的信息
        HttpServletRequest request = ((FilterInvocation) object).getHttpRequest();
        for (Map.Entry<String, Collection<ConfigAttribute>> entry : map.entrySet()) {
            String url = entry.getKey();
            if (new AntPathRequestMatcher(url).matches(request)) {
                configAttributes = map.get(url);
            }
        }
        // 防止数据库中没有数据，不能进行权限拦截,为空的话 @{links AbstractSecurityInterceptor}的beforeInvocation会直接返回null,啥都不做
        // 以默认用户去进行逻辑, 并且不会做任何鉴权判断(不知道为啥这样设计)
        if (configAttributes.size() < 1) {
            ConfigAttribute configAttribute = new SecurityConfig("ROLE_NO_USER");
            configAttributes.add(configAttribute);
        }
        return configAttributes;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    /**
     * 方法返回类对象是否支持校验
     * web项目一般使用FilterInvocation来判断，或者直接返回true
     *
     * @param clazz
     * @return
     */
    @Override
    public boolean supports(Class<?> clazz) {
        return FilterInvocation.class.isAssignableFrom(clazz);
    }
}
