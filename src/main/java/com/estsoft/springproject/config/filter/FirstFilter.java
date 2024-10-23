package com.estsoft.springproject.config.filter;

import jakarta.servlet.*;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

public class FirstFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
        System.out.println("FirstFilter init");
    }

    @Override
    public void destroy() {
        System.out.println("FirstFilter.destroy()");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("FirstFilter.doFilter()REQ");
        //request URI
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        System.out.println("request URI :" +request.getRequestURI());

        filterChain.doFilter(servletRequest, servletResponse);
        System.out.println("FirstFilter.doFilter().RES");
        //
    }
}
