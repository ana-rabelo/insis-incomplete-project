package com.isep.acme.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductDetailDTO {
    private String sku;
    private String designation;
    private String description;
}
