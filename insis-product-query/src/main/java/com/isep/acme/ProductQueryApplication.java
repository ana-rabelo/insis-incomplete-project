package com.isep.acme;

import java.awt.image.BufferedImage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.BufferedImageHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;

import com.isep.acme.property.FileStorageProperties;

@SpringBootApplication
@EnableConfigurationProperties({
		FileStorageProperties.class
})
public class ProductQueryApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductQueryApplication.class, args);
	}

	@Bean
	public HttpMessageConverter<BufferedImage> createImageHttpMessageConverter() {
		return new BufferedImageHttpMessageConverter();
	}
}
