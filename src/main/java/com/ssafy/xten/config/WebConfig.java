package com.ssafy.xten.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");

		registry.addResourceHandler("/swagger-ui/**")
				.addResourceLocations("classpath:/META-INF/resources/webjars/springfox-swagger-ui/");
	}
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// 인터셉터 필요하면 등록. 
	}
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		//registry.addMapping("/**").allowedOrigins("*");
		registry.addMapping("/**").allowedOrigins("*").allowedMethods("GET","POST","PUT","DELETE"); //그냥 별로 퉁치는 것 보다 이게 확실하다. 
	}
}
