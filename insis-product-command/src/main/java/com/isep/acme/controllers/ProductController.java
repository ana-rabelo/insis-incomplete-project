package com.isep.acme.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.isep.acme.dtos.ProductDTO;
import com.isep.acme.messaging.ProductProducer;
import com.isep.acme.model.Product;
import com.isep.acme.services.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Product", description = "Endpoints for managing  products")
@RestController
@RequestMapping("/products")
class ProductController {

    @Autowired
    private ProductService service;

    @Autowired
    private ProductProducer productProducer;

    @Operation(summary = "creates a product")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ProductDTO> create(@RequestBody Product product) {
        try {
            final ProductDTO productDTO = service.create(product);

            productProducer.sendCreatedProductMessage(product);

            return new ResponseEntity<ProductDTO>(productDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace(); 
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Product must have a unique SKU.");
        }
    }

    @Operation(summary = "updates a product")
    @PatchMapping(value = "/{sku}")
    public ResponseEntity<Product> update(@PathVariable("sku") final String sku,
            @RequestBody final Product product) {

        final Product productUp = service.updateBySku(sku, product);
        productProducer.sendUpdatedProductMessage(productUp);

        if (productUp == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found.");
        else
            return ResponseEntity.ok().body(productUp);
    }

    @Operation(summary = "deletes a product")
    @DeleteMapping(value = "/{sku}")
    public ResponseEntity<String> delete(@PathVariable("sku") final String sku) {

        service.deleteBySku(sku);
        productProducer.sendDeletedProductMessage(sku);
        return ResponseEntity.ok("Product deleted.");
    }
}