package com.bomp.config;

import java.io.IOException;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@EnableWebMvc
@Configuration
@ComponentScan(basePackages = {"com.bomp.controller", "com.bomp.exception"})
public class ServletConfig  implements WebMvcConfigurer{
	
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		InternalResourceViewResolver bean = new InternalResourceViewResolver();
		bean.setViewClass(JstlView.class);
		bean.setPrefix("/WEB-INF/views/");
		bean.setSuffix(".jsp");
		registry.viewResolver(bean);
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/css/**").addResourceLocations("/resources/assets/css/");
		registry.addResourceHandler("/js/**").addResourceLocations("/resources/assets/js/");
		registry.addResourceHandler("/sass/**").addResourceLocations("/resources/assets/sass/");
		registry.addResourceHandler("/webfonts/**").addResourceLocations("/resources/assets/webfonts/");
		registry.addResourceHandler("/images/**").addResourceLocations("/resources/images/");
	}
	
	@Bean(name = "multipartResolver")
	public CommonsMultipartResolver getResolver() throws IOException {
		CommonsMultipartResolver resolver = new CommonsMultipartResolver();
		resolver.setMaxUploadSize(1024 * 1024 * 2);
		resolver.setMaxUploadSizePerFile(1024 * 1024 * 2);
		resolver.setMaxInMemorySize(1024 * 1024);
		resolver.setUploadTempDir(new FileSystemResource("C:\\upload\\tmp"));
		resolver.setDefaultEncoding("UTF-8");
		return resolver;
	}
	
	@Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add(new StringHttpMessageConverter());
		converters.add(new MappingJackson2HttpMessageConverter());
    }	
}
