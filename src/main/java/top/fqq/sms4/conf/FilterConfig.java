package top.fqq.sms4.conf;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.fqq.sms4.filter.RequestFilter;

/**
 * @Auther: fqq
 * @Date: 2019/8/15 11:05
 * @Description:
 */
@Configuration
public class FilterConfig {
    @Bean
    public FilterRegistrationBean filterRegistrationBean(){
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new RequestFilter());
        bean.addUrlPatterns("/*");
        return bean;
    }
}
