package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 
 * @author
 *
 */
@EnableWebMvc
@Configuration
@ComponentScan
public class WebMvcConfig implements WebMvcConfigurer {

	/**
	 * This method will be used to configure global cors mapping
	 * 
	 */
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**");
		// .allowedOrigins("http://domain2.com")
		// .allowedMethods("PUT", "DELETE")
		// .allowedHeaders("header1", "header2", "header3")
		// .exposedHeaders("header1", "header2")
		// .allowCredentials(false).maxAge(3600);
	}

	// @Override
	// public void configureContentNegotiation(ContentNegotiationConfigurer
	// configurer) {
	// configurer.defaultContentType(MediaType.APPLICATION_JSON);
	// }

	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		registry.jsp("/WEB-INF/jsp/", ".jsp");
	}

	// Use either configureViewResolvers or below
	// @Bean
	// public InternalResourceViewResolver jspViewResolver() {
	// InternalResourceViewResolver resolver= new InternalResourceViewResolver();
	// resolver.setPrefix("/WEB-INF/jsp/");
	// resolver.setSuffix(".jsp");
	// return resolver;
	// }

	// Directly adding a view for a url
	// @Override
	// public void addViewControllers(ViewControllerRegistry registry) {
	// //this will map uri to jsp view directly without a controller
	// registry.addViewController("/hi")
	// .setViewName("hello");
	// }
}