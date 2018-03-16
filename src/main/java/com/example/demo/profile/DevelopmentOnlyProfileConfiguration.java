package com.example.demo.profile;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@Profile("dev")
public class DevelopmentOnlyProfileConfiguration {

	@Bean
	public String dummy() {
		return "Development Profile Data";
	}
}
