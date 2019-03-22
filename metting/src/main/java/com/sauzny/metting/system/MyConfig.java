package com.sauzny.metting.system;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/***************************************************************************
 *
 * @时间: 2019/3/15 - 16:21
 *
 * @描述: TODO
 *
 ***************************************************************************/
@Configuration
public class MyConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }
}
