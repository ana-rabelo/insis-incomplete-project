package com.isep.acme.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isep.acme.dtos.ProductDTO;
import com.isep.acme.model.Product;
import com.isep.acme.repositories.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository repository;

    @Override
    public ProductDTO create(final Product product) {
        Product p = new Product();
        p.setSku(product.getSku());
        p.setDesignation(product.getDesignation());
        p.setDescription(product.getDescription());

        return repository.save(p).toDto();
    }

    @Override
    public Product updateBySku(String sku, Product product) {

        final Product productToUpdate = repository.findBySku(sku)
                .orElseThrow(() -> new RuntimeException("Product not found."));

        productToUpdate.updateProduct(product);

        Product productUpdated = repository.save(productToUpdate);

        return productUpdated;
    }

    @Override
    public void deleteBySku(String sku) {
        repository.deleteBySku(sku);
    }

    public boolean existsBySku(String sku) {
        final Optional<Product> product = repository.findBySku(sku);

        return product != null;
    }
}
