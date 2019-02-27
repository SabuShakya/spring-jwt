package com.sabu.jwtfilter;

import com.sabu.utility.JWTUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.rmi.ServerException;

public class JWTFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        final HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;

        String uri = httpServletRequest.getRequestURI();

        //AUTHENICATION FOR LOGIN API IS IGNORED
        if (!uri.startsWith("/login")) {
            //AUTHENTICATION STARTS HERE
            authenticateAndValidateRequest(httpServletRequest);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    private void authenticateAndValidateRequest(HttpServletRequest httpServletRequest) throws ServerException {

        final String header = httpServletRequest.getHeader("Authorization");

        if (header == null || !header.startsWith("Bearer")) {
            throw new ServerException("Invalid access.");
        }

        if (!JWTUtils.validateJWTToken(httpServletRequest, header)) {
            throw new ServerException("Invalid Access");
        }

    }

    @Override
    public void destroy() {

    }
}
