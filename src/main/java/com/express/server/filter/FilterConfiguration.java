package com.express.server.filter;

import jakarta.annotation.Resource;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author youping.tan
 * @date 2024/8/6 17:46
 */
@Configuration
public class FilterConfiguration {

    @Resource
    private LoginSessionFilter globalFilter;

    @Bean
    public FilterRegistrationBean<LoginSessionFilter> globalFilter1() {
        FilterRegistrationBean<LoginSessionFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(globalFilter);
        registrationBean.addUrlPatterns("/*"); // 配置过滤器的 URL 模式
        registrationBean.setOrder(1); // 设置过滤器的优先级
        return registrationBean;
    }
}