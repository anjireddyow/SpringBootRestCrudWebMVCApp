package com.example.demo.profile;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@Profile("prod")
public class ProductionOnlyProfileConfiguration {

	@Bean
	public String productionValue() {
		return "Production Profile Configuration";
	}
}
