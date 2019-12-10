package com.lanzhou.yuanfen.security.token;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * 两个构造方法需要引起我们的注意，这里设计的用意是模仿的UsernamePasswordAuthenticationToken，
 * 第一个构造器是用于认证之前，传递给认证器使用的，所以只有IP地址，自然是未认证；
 * 第二个构造器用于认证成功之后，封装认证用户的信息，此时需要将权限也设置到其中，
 * 并且setAuthenticated(true)。这样的设计在诸多的Token类设计中很常见。
 *
 * @author Administrator
 */
public class EmailAuthenticationToken extends AbstractAuthenticationToken {

    /**
     * 邮箱
     */
    private String email;

    /**
     * 验证码
     */
    private String eCode;

    /**
     * 这个构造方法是认证是使用的
     *
     * @param email
     * @param eCode
     */
    public EmailAuthenticationToken(String email, String eCode) {
        super(null);
        this.email = email;
        this.eCode = eCode;
        super.setAuthenticated(false);
    }

    /**
     * 注意是认证成功后使用的
     *
     * @param authorities
     * @param email
     * @param eCode
     */
    public EmailAuthenticationToken(String email, String eCode, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.email = email;
        this.eCode = eCode;
        super.setAuthenticated(true);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String geteCode() {
        return eCode;
    }

    public void seteCode(String eCode) {
        this.eCode = eCode;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return null;
    }
}
