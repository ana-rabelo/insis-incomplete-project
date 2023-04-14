package com.isep.acme.services;

import org.springframework.stereotype.Service;

import com.isep.acme.dtos.ProductDTO;
import com.isep.acme.model.Product;

@Service
public interface ProductService {

    ProductDTO create(final Product manager);

    Product updateBySku(final String sku, final Product product);

    void deleteBySku(final String sku);    
}
