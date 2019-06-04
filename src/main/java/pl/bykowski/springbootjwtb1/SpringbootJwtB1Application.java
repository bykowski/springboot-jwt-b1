package pl.bykowski.springbootjwtb1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import java.util.Collections;

@SpringBootApplication
public class SpringbootJwtB1Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootJwtB1Application.class, args);
    }


    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filters = new FilterRegistrationBean();
        filters.setFilter(new JwetFilter());
        filters.setUrlPatterns(Collections.singleton("/api/hello/*"));
        return filters;
    }


}
