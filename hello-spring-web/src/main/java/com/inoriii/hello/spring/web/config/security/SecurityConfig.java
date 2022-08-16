package com.inoriii.hello.spring.web.config.security;

import com.inoriii.hello.spring.api.service.UserService;
import com.inoriii.hello.spring.api.vo.FetchUserRoleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sakura
 * @date: 2022/7/23 16:38
 * @description:
 */
@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Autowired
    private UserService userService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsServiceBean() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                FetchUserRoleVO user = userService.fetchUserRoleVO(username);
                if (user == null) {
                    throw new UsernameNotFoundException("用户： " + username + " 不存在！");
                }
                List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
                List<FetchUserRoleVO.RoleVO> roles = user.getRoleList();
                roles.forEach(r -> {
                    grantedAuthorities.add(new SimpleGrantedAuthority(r.getRoleName()));
                });
                return new User(user.getUsername(), user.getPassword(), grantedAuthorities);
            }
        };
    }


    /**
     * 修改默认的httpSecurity
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests().
//                mvcMatchers("/login.html").permitAll().
        anyRequest().authenticated().and().formLogin()
//                .loginPage("/login.html").loginProcessingUrl("/login").usernameParameter("username").passwordParameter("password").defaultSuccessUrl("/index.html").failureForwardUrl("/login.html").
//                and().
//                logout().logoutUrl("/logout").
//                and().csrf().disable()
        ;
        return http.build();
    }

    /**
     * 另一条DefaultSecurityFilterChain（自定义）
     */
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring().antMatchers(HttpMethod.GET);
    }

}
