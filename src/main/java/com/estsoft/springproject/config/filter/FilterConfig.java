package com.estsoft.springproject.config.filter;

import jakarta.servlet.Filter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {
    @Bean
    public FilterRegistrationBean<Filter> firstFilter() {
        FilterRegistrationBean<Filter> filter = new FilterRegistrationBean<>();

        filter.setFilter(new FirstFilter());
        filter.addUrlPatterns("/books");
        return filter;
    }
}
