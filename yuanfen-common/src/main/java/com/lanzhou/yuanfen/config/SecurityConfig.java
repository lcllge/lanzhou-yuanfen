package com.lanzhou.yuanfen.config;

import com.lanzhou.yuanfen.security.handler.MyAuthenticationFailureHandler;
import com.lanzhou.yuanfen.security.handler.MyAuthenticationSuccessHandler;
import com.lanzhou.yuanfen.security.handler.AuthenticationAccessDeniedHandler;
import com.lanzhou.yuanfen.security.mgt.MyAccessDecisionManager;
import com.lanzhou.yuanfen.security.MyFilterInvocationSecurityMetadataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

import javax.annotation.Resource;

/**
 * @author Administrator
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private MyUserDetailsService myUserDetailsService;

    @Resource
    private MyFilterInvocationSecurityMetadataSource myFilterInvocationSecurityMetadataSource;

    @Resource
    private MyAccessDecisionManager myAccessDecisionManager;

    @Resource
    private AuthenticationAccessDeniedHandler authenticationAccessDeniedHandler;

    @Resource
    private MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;

    @Resource
    private MyAuthenticationFailureHandler myAuthenticationFailureHandler;

    /**
     * 自定义的加密算法
     *
     * @return
     */
    @Bean
    public PasswordEncoder myPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailsService).passwordEncoder(myPasswordEncoder());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        String[] ignoreMatchers = new String[]{
                "/loginPage",
                "/register",
                "/sendEmailPage",
                "/registerUser",
                "/sendEmail",
                "/sendSms",
                "/bootstrap/**",
                "/layer/**",
                "/fragments/**"
        };
        web.ignoring().antMatchers(ignoreMatchers);
    }

    /**
     * .exceptionHandling().authenticationEntryPoint(new UnauthorizedEntryPoint()).and()
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O o) {
                        o.setSecurityMetadataSource(myFilterInvocationSecurityMetadataSource);
                        o.setAccessDecisionManager(myAccessDecisionManager);
                        return o;
                    }
                }).anyRequest().authenticated()
                .and().formLogin().loginPage("/loginPage")
                .loginProcessingUrl("/login").usernameParameter("username")
                .passwordParameter("password").defaultSuccessUrl("/").permitAll()
                .failureHandler(myAuthenticationFailureHandler)
                .successHandler(myAuthenticationSuccessHandler)
                .and()
                .logout()
                //自定义退出的地址
                .logoutUrl("/signOut")
                //退出之后跳转到注册页面
                .logoutSuccessUrl("/")
                .deleteCookies("JSESSIONID")
                .permitAll()
                .and().csrf().disable()
                .exceptionHandling()
                .accessDeniedHandler(authenticationAccessDeniedHandler);
    }


}
