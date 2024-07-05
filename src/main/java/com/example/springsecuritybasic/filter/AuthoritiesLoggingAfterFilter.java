package com.example.springsecuritybasic.filter;

import com.example.springsecuritybasic.controller.restController.SpringSecurityAPIController;
import jakarta.servlet.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.io.IOException;

public class AuthoritiesLoggingAfterFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(AuthoritiesLoggingAfterFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(null != authentication) {
            logger.info("User " + authentication.getName() + " is successfully authenticated and has the authorities " + authentication.getAuthorities().toString());
        }

        chain.doFilter(request, response);
    }
}
