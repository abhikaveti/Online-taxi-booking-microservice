package com.calltaxi.api_gateway.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfigs {

	
	@Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
