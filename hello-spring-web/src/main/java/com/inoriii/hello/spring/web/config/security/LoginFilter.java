package com.inoriii.hello.spring.web.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.inoriii.hello.spring.api.enums.ResponseCode;
import com.inoriii.hello.spring.common.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * @author sakura
 */
public class LoginFilter extends UsernamePasswordAuthenticationFilter {
    @Autowired
    private RememberMeServices rememberMeServices;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (!request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }
        if (!MediaType.APPLICATION_JSON_VALUE.equalsIgnoreCase(request.getContentType())) {
            return super.attemptAuthentication(request, response);
        }
        Map map = null;
        try {
            map = new ObjectMapper().readValue(request.getInputStream(), Map.class);
        } catch (IOException e) {
            throw new BusinessException(ResponseCode.FAIL);
        }
        String username = (String) map.get(getUsernameParameter());
        String password = (String) map.get(getPasswordParameter());
        if (rememberMeServices instanceof PersistentTokenBasedRememberMeServices) {
            String rememberMeParameterName = ((PersistentTokenBasedRememberMeServices) rememberMeServices).getParameter();
            String rememberMe = (String) map.get(rememberMeParameterName);
            request.setAttribute(rememberMeParameterName, rememberMe);
        }
        UsernamePasswordAuthenticationToken authRequest = UsernamePasswordAuthenticationToken.unauthenticated(username, password);
        // Allow subclasses to set the "details" property
        setDetails(request, authRequest);
        return this.getAuthenticationManager().authenticate(authRequest);
    }
}