package com.isep.acme.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ProductDTO {
    private String sku;
    private String designation;
}
