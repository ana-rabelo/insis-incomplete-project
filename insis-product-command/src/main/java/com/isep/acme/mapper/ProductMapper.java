package com.isep.acme.mapper;

import java.io.IOException;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.isep.acme.model.Product;

@Component
public class ProductMapper {
    
    public Product createProductFromMessage(String body){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Product product = objectMapper.readValue(body, Product.class);
            return product;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    
}
