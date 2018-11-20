package com.zzpdream.hotel;

import com.zzpdream.hotel.security.CustomizeAuthenticationSuccessHandler;
import com.zzpdream.hotel.security.MyUserDetailsService;
import com.zzpdream.hotel.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    CustomizeAuthenticationSuccessHandler customizeAuthenticationSuccessHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //表单登录，permitAll()表示这个不需要验证 登录页面，登录失败页面
        http
                .authorizeRequests()
                .antMatchers("/pics/**",
                        "/plugins/**",
                        "/css/**",
                        "/js/**",
                        "/build/css/**",
                        "/build/css/**",
                        "/fonts/**",
                        "/**/favicon.ico").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
//                .successHandler(customizeAuthenticationSuccessHandler)
                .loginPage("/login")
//                .successForwardUrl("/main")
                .permitAll()
                .and()
                .logout()
                .permitAll()
                .and()
                .headers().frameOptions().disable().and()
                .rememberMe().tokenValiditySeconds(1209600)
                //指定记住登录信息所使用的数据源
                .and()//code4.and()
                .csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // TODO Auto-generated method stub
        auth.userDetailsService(userDetailsService()).passwordEncoder(new PasswordEncoder() {
            @Override
            public String encode(CharSequence rawPassword) {
                return MD5Util.encode((String)rawPassword);
            }

            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                return encodedPassword.equals(MD5Util.encode((String)rawPassword));
            }});
    }

    @Bean
    public UserDetailsService userDetailsService() {
        MyUserDetailsService myUserDetailsService = new MyUserDetailsService();
        return myUserDetailsService;
    }
}
