package com.isep.acme;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.BufferedImageHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;

import com.isep.acme.property.FileStorageProperties;

import java.awt.image.BufferedImage;

@EnableDiscoveryClient
@SpringBootApplication
@EnableConfigurationProperties({
		FileStorageProperties.class
})
public class VoteCommandApplication {

	public static void main(String[] args) {
		SpringApplication.run(VoteCommandApplication.class, args);
	}

	@Bean
	public HttpMessageConverter<BufferedImage> createImageHttpMessageConverter() {
		return new BufferedImageHttpMessageConverter();
	}
}
