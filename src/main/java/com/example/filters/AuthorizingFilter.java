package com.example.filters;

import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component("loggingFilter")
public class AuthorizingFilter implements Filter {
    @Autowired
    private UserService userService;

    @Override
    public void destroy() {
    }

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        String login = (String) httpServletRequest.getSession().getAttribute("login");
        String sessionId = httpServletRequest.getSession().getId();
        String requestURI = httpServletRequest.getRequestURI();
        String contextPath = httpServletRequest.getContextPath();

        if(login != null && !userService.containsLoginSessionId(login, sessionId)){
            httpServletRequest.getSession().removeAttribute("login");
            httpServletResponse.sendRedirect(contextPath + "/app/login");
        }

        if (login == null && !(requestURI.startsWith(contextPath + "/app/login"))) {
            httpServletResponse.sendRedirect(contextPath + "/app/login");
        } else if (login != null && requestURI.startsWith(contextPath + "/app/login")) {
            httpServletResponse.sendRedirect(contextPath + "/app/welcome");
        } else {
            chain.doFilter(request, response);
        }

    }

}