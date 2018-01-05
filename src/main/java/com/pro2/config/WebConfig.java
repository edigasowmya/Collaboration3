package com.pro2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@EnableWebMvc 
//<mvc:annotation-driven></mvc:annotation-driven>
@ComponentScan(basePackages="com.pro2")
//<context:component-scan base-package="com.niit"></context:component-scan>
public class WebConfig extends WebMvcConfigurerAdapter{

	/*@Bean
	public ViewResolver viewResolver(){
		
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/pages");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}
	*/
}
