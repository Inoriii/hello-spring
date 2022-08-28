package com.inoriii.hello.spring.web.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.inoriii.hello.spring.api.enums.ResponseCode;
import com.inoriii.hello.spring.api.service.UserService;
import com.inoriii.hello.spring.api.vo.FetchUserRoleVO;
import com.inoriii.hello.spring.api.vo.RestResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.vote.AbstractAccessDecisionManager;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.UrlAuthorizationConfigurer;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.session.Session;
import org.springframework.session.security.SpringSessionBackedSessionRegistry;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * @author sakura
 * @date: 2022/7/23 16:38
 * @description:
 */
@EnableWebSecurity(debug = true)
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
public class SpringSecurityConfig {

    @Autowired
    private UserService userService;
    @Autowired
    private MySecurityMetadataSource mySecurityMetadataSource;
    @Autowired
    private FindByIndexNameSessionRepository<? extends Session> sessionRegistry;

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                FetchUserRoleVO user = userService.fetchUserRoleVO(username);
                if (user == null) {
                    throw new UsernameNotFoundException("用户： " + username + " 不存在！");
                }
                List<String> permissionList = new ArrayList<>();
                List<FetchUserRoleVO.PermissionVO> userPermissionList = user.getPermissionList();
                if (!CollectionUtils.isEmpty(userPermissionList)) {
                    for (FetchUserRoleVO.PermissionVO permissionVO : userPermissionList) {
                        String permissionName = permissionVO.getPermissionName();
                        if (!StringUtils.hasText(permissionName)) {
                            continue;
                        }
                        permissionList.add(permissionName);
                    }
                }
                List<FetchUserRoleVO.RoleVO> roles = user.getRoleList();
                if (!CollectionUtils.isEmpty(roles)) {
                    for (FetchUserRoleVO.RoleVO roleVO : roles) {
                        String roleName = roleVO.getRoleName();
                        if (!StringUtils.hasText(roleName)) {
                            continue;
                        }
                        permissionList.add("ROLE_" + roleName);
                    }
                }
                return User.withUsername(user.getUsername()).password(user.getPassword()).authorities(permissionList.toArray(String[]::new)).build();
            }
        };
    }

    /**
     * 当加密方式改变后自动修改数据库存储的密码
     *
     * @return
     */
    @Bean
    public UserDetailsPasswordService userDetailsPasswordService() {
        return new UserDetailsPasswordService() {
            @Override
            public UserDetails updatePassword(UserDetails user, String newPassword) {
                userService.updateUserPassword(user.getUsername(), newPassword);
                return user;
            }
        };
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public LoginFilter loginFilter(AuthenticationManager authenticationManager, RememberMeServices rememberMeServices) {
        LoginFilter loginFilter = new LoginFilter();
        loginFilter.setFilterProcessesUrl("/login");
        loginFilter.setUsernameParameter("username");
        loginFilter.setPasswordParameter("password");
        loginFilter.setAuthenticationManager(authenticationManager);
        //第一次登录，勾选记住我则触发PersistentTokenRepository持久化
        loginFilter.setRememberMeServices(rememberMeServices);
        loginFilter.setAuthenticationSuccessHandler((request, response, authentication) -> {
            response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().println(new ObjectMapper().writeValueAsString(new RestResult<>(authentication.getPrincipal())));
        });
        loginFilter.setAuthenticationFailureHandler((request, response, exception) -> {
            response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().println(new ObjectMapper().writeValueAsString(new RestResult<>(ResponseCode.LOGIN)));
        });
        return loginFilter;
    }

    /**
     * 前后端分离方式
     * 修改默认的httpSecurity
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, LoginFilter loginFilter, RememberMeServices rememberMeServices) throws Exception {
        http.apply(new UrlAuthorizationConfigurer(http.getSharedObject(ApplicationContext.class)).withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
            @Override
            public <O extends FilterSecurityInterceptor> O postProcess(O object) {
                object.setSecurityMetadataSource(mySecurityMetadataSource);
                AccessDecisionManager accessDecisionManager = object.getAccessDecisionManager();
                if (accessDecisionManager instanceof AbstractAccessDecisionManager) {
                    ((AbstractAccessDecisionManager) accessDecisionManager).getDecisionVoters().add(new MyDecisionVoter());
                }
                object.setRejectPublicInvocations(true);
                return object;
            }
        }));
        http.addFilterAt(loginFilter, UsernamePasswordAuthenticationFilter.class);
        http.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
        http.cors().configurationSource(new UrlBasedCorsConfigurationSource() {
            {
                CorsConfiguration configuration = new CorsConfiguration();
                configuration.setAllowedHeaders(Arrays.asList("*"));
                configuration.setAllowedMethods(Arrays.asList("*"));
                configuration.setAllowedOrigins(Arrays.asList("*"));
                configuration.setMaxAge(Duration.ofDays(1));
                registerCorsConfiguration("/**", configuration);
            }
        });
        http.rememberMe().rememberMeServices(rememberMeServices);
        http.sessionManagement().maximumSessions(1).expiredSessionStrategy(event -> {
            HttpServletResponse response = event.getResponse();
            response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().println(new ObjectMapper().writeValueAsString(new RestResult<>(ResponseCode.SESSION_INVALIDATED)));
        }).sessionRegistry(sessionRegistry()).maxSessionsPreventsLogin(true);
        http.logout().logoutUrl("/logout").logoutSuccessHandler((request, response, authentication) -> {
            response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().println(new ObjectMapper().writeValueAsString(new RestResult<>()));
        });
        http.exceptionHandling().authenticationEntryPoint((request, response, exception) -> {
            response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().println(new ObjectMapper().writeValueAsString(new RestResult<>(-1, exception.getMessage())));
        }).accessDeniedHandler((request, response, exception) -> {
            response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().println(new ObjectMapper().writeValueAsString(new RestResult<>(-1, exception.getMessage())));
        });
        return http.build();
    }

    /**
     * 记住我，重写了判断【前端传递记住我参数】是否开启。
     *
     * @param userDetailsService
     * @param persistentTokenRepository
     * @return
     */
    @Bean
    public RememberMeServices rememberMeServices(UserDetailsService userDetailsService, PersistentTokenRepository persistentTokenRepository) {
        return new PersistentTokenBasedRememberMeServices(UUID.randomUUID().toString(), userDetailsService, persistentTokenRepository) {
            protected boolean rememberMeRequested(HttpServletRequest request, String parameter) {
                Object paramValue = request.getAttribute(parameter);
                if (paramValue != null && paramValue instanceof String) {
                    String attribute = (String) paramValue;
                    if (attribute.equalsIgnoreCase("true") || attribute.equalsIgnoreCase("on") || attribute.equalsIgnoreCase("yes") || attribute.equals("1")) {
                        return true;
                    }
                }
                return false;
            }
        };
    }

    /**
     * 持久化series
     *
     * @param dataSource
     * @return
     */
    @Bean
    public PersistentTokenRepository persistentTokenRepository(DataSource dataSource) {
        return new JdbcTokenRepositoryImpl() {
            {
                setDataSource(dataSource);
//                第一次创建表才需要
//                setCreateTableOnStartup(true);
            }
        };
    }

    /**
     * 另一条DefaultSecurityFilterChain（自定义）
     */
//    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring().antMatchers(HttpMethod.GET);
    }

    @Bean
    public SpringSessionBackedSessionRegistry sessionRegistry() {
        return new SpringSessionBackedSessionRegistry<>(sessionRegistry);
    }
}
