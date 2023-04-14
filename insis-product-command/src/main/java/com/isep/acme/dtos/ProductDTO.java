package com.isep.acme.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter @Setter
public class ProductDTO {
    private String sku;
    private String designation;
    private String description;
}
